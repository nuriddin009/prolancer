package com.prolancer.FreelanceBazar.repository.impl;

import com.prolancer.FreelanceBazar.entity.Payment;
import com.prolancer.FreelanceBazar.filter.PaymentFilter;
import com.prolancer.FreelanceBazar.repository.custom.CustomPaymentRepository;
import com.prolancer.FreelanceBazar.utils.QueryUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentRepositoryImpl implements CustomPaymentRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<Payment> findAllByFilter(PaymentFilter filter) {
        final boolean hasSearch = StringUtils.isNotBlank(filter.getSearch());
        final boolean sorted = filter.formPageable().getSort().isSorted();

        StringBuilder sql = new StringBuilder("select t from Payment t where t.deleted=false ");

        if (hasSearch) {
            sql.append(" and lower(t.description) like :searchKey ");
        }
        String countSql = sql.toString().replace("select t", "select count(t)");


        QueryUtils.appendQuery(sorted, filter, sql);


        TypedQuery<Payment> query = entityManager.createQuery(sql.toString(), Payment.class);
        TypedQuery<Long> countQuery = entityManager.createQuery(countSql, Long.class);

        if (hasSearch) {
            query.setParameter("searchKey", filter.getSearchForQuery());
            countQuery.setParameter("searchKey", filter.getSearchForQuery());
        }


        return new PageImpl<>(query.getResultList(), filter.getPageable(), countQuery.getSingleResult());
    }
}
