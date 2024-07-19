package com.prolancer.FreelanceBazar.repository.impl;

import com.prolancer.FreelanceBazar.entity.Contract;
import com.prolancer.FreelanceBazar.filter.ContractFilter;
import com.prolancer.FreelanceBazar.payload.response.ContractResponse;
import com.prolancer.FreelanceBazar.repository.custom.CustomContractRepository;
import com.prolancer.FreelanceBazar.repository.page.ResponsePage;
import com.prolancer.FreelanceBazar.repository.page.ResponsePageImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

@Repository
public class ContractRepositoryImpl implements CustomContractRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ResponsePage<ContractResponse> findAllContractsByFilter(ContractFilter filter) {
        final boolean sorted = filter.formPageable().getSort().isSorted();
        final boolean hasSearch = StringUtils.isNotEmpty(filter.getSearch());


        StringBuilder sql = new StringBuilder("select t from Contract t ");

        if (hasSearch) {
            sql.append(" and ");
        }


        String countSql = sql.toString().replace("select t", "select count(t)");

        TypedQuery<Contract> query = entityManager.createQuery(sql.toString(), Contract.class);
        TypedQuery<Long> countQuery = entityManager.createQuery(countSql, Long.class);

        if (hasSearch) {
            query.setParameter("searchKey", filter.getSearchForQuery());
            countQuery.setParameter("searchKey", filter.getSearchForQuery());
        }


        return new ResponsePageImpl<>();
    }
}
