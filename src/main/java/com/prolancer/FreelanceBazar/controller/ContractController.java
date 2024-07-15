package com.prolancer.FreelanceBazar.controller;

import com.prolancer.FreelanceBazar.entity.enums.ContractStatus;
import com.prolancer.FreelanceBazar.filter.ContractFilter;
import com.prolancer.FreelanceBazar.payload.model.ApiResponse;
import com.prolancer.FreelanceBazar.payload.request.ContractRequest;
import com.prolancer.FreelanceBazar.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/contract")
public class ContractController {

    private final ContractService service;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllContracts(@ParameterObject ContractFilter filter) {
        return ResponseEntity.ok(service.getAllContracts(filter));
    }

    @GetMapping("/byId")
    public ResponseEntity<ApiResponse> getContractById(@RequestParam UUID contractId) {
        return ResponseEntity.ok(service.getContractById(contractId));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createContract(@RequestBody ContractRequest request) {
        return ResponseEntity.ok(service.createContract(request));
    }

    @PutMapping("{contractId}")
    public ResponseEntity<ApiResponse> updateContractStatus(@PathVariable UUID contractId, @RequestBody ContractStatus status) {
        return ResponseEntity.ok(service.updateContractStatus(contractId, status));
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse> deleteContract(@RequestParam UUID contractId) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(service.deleteContract(contractId));
    }


}
