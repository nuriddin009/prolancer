package com.prolancer.FreelanceBazar.service.impl;

import com.prolancer.FreelanceBazar.entity.Payment;
import com.prolancer.FreelanceBazar.entity.User;
import com.prolancer.FreelanceBazar.exceptions.ResourceNotFoundException;
import com.prolancer.FreelanceBazar.filter.PaymentFilter;
import com.prolancer.FreelanceBazar.payload.model.ApiResponse;
import com.prolancer.FreelanceBazar.payload.request.CardRequest;
import com.prolancer.FreelanceBazar.payload.request.PaymentRequest;
import com.prolancer.FreelanceBazar.payload.response.PaymentResponse;
import com.prolancer.FreelanceBazar.repository.PaymentRepository;
import com.prolancer.FreelanceBazar.repository.UserRepository;
import com.prolancer.FreelanceBazar.service.PaymentService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentMethod;
import com.stripe.param.PaymentIntentCreateParams;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;
    private final UserSession userSession;

    @Value("${application.stripe.secret-key}")
    private String secretKey;


    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
    }


    @Transactional(readOnly = true)
    @Override
    public ApiResponse getPaymentList(PaymentFilter filter) {

        Page<Payment> pagedPayments = paymentRepository.findAllByFilter(filter);
        List<PaymentResponse> paymentList = pagedPayments.getContent().stream().map(payment ->
                PaymentResponse.builder()
                        .amount(payment.getAmount())
                        .payType(payment.getPayType())
                        .createdBy(getFrom(payment.getCreatedBy()))
                        .build()
        ).toList();

        return ApiResponse.successResponse(paymentList, "Payment list");
    }

    @Override
    public ResponseEntity<?> makePaymentUsingCard(String paymentMethodId, String customerId, long amount) {
        Stripe.apiKey = secretKey;

        try {
            Customer.retrieve(customerId);

            PaymentIntentCreateParams createParams = new PaymentIntentCreateParams.Builder()
                    .setAmount(amount)
                    .setCurrency("usd")
                    .setPaymentMethod(paymentMethodId)
                    .setCustomer(customerId)
                    .setConfirmationMethod(PaymentIntentCreateParams.ConfirmationMethod.AUTOMATIC)
                    .setConfirm(true)
                    .build();

            PaymentIntent paymentIntent = PaymentIntent.create(createParams);

            return ResponseEntity.ok().body("Payment successful with payment intent ID: " + paymentIntent.getId());
        } catch (StripeException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> addCard(CardRequest cardRequest) {
        Stripe.apiKey = secretKey;
        String email = userSession.getEmail();

        Map<String, Object> cardParams = new HashMap<>();
        cardParams.put("number", cardRequest.getCardNumber());
        cardParams.put("exp_month", cardRequest.getExpirationMonth());
        cardParams.put("exp_year", cardRequest.getExpirationYear());
        cardParams.put("cvc", cardRequest.getCvc());

        Map<String, Object> paymentMethodParams = new HashMap<>();
        paymentMethodParams.put("type", "card");
        paymentMethodParams.put("card", cardParams);

        try {
            PaymentMethod paymentMethod = PaymentMethod.create(paymentMethodParams);

            Map<String, Object> customerParams = new HashMap<>();
            customerParams.put("email", email);
            customerParams.put("payment_method", paymentMethod.getId());

            Customer customer = Customer.create(customerParams);

            return ResponseEntity.ok().body("Card added successfully to customer " + customer.getId());
        } catch (StripeException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> charge(PaymentRequest paymentRequest) {
        Stripe.apiKey = secretKey;

        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", paymentRequest.getAmount());
        chargeParams.put("currency", paymentRequest.getCurrency());
        chargeParams.put("description", paymentRequest.getDescription());
        chargeParams.put("source", paymentRequest.getSource());

        try {
            Charge charge = Charge.create(chargeParams);

            return ResponseEntity.ok().body("Payment successful with charge ID: " + charge.getId());
        } catch (StripeException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    private String getFrom(String email) {
        if (email.equals("anonymous") || email.equals("system"))
            throw new ResourceNotFoundException("Not found");
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found for this email -> " + email));
        return String.format("%s %s", user.getFirstname(), user.getLastname());
    }
}
