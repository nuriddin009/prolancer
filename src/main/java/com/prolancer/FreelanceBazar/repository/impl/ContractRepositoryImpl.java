package com.prolancer.FreelanceBazar.repository.impl;

import com.prolancer.FreelanceBazar.filter.ContractFilter;
import com.prolancer.FreelanceBazar.payload.response.ContractResponse;
import com.prolancer.FreelanceBazar.repository.custom.CustomContractRepository;
import com.prolancer.FreelanceBazar.repository.page.ResponsePage;
import com.prolancer.FreelanceBazar.repository.page.ResponsePageImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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


        return new ResponsePageImpl<>();
    }
}
