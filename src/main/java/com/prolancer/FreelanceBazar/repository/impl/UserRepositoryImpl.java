package com.prolancer.FreelanceBazar.repository.impl;

import com.prolancer.FreelanceBazar.entity.User;
import com.prolancer.FreelanceBazar.filter.UserFilter;
import com.prolancer.FreelanceBazar.payload.response.UserResponse;
import com.prolancer.FreelanceBazar.repository.custom.UserCompositeRepository;
import com.prolancer.FreelanceBazar.repository.page.ResponsePage;
import com.prolancer.FreelanceBazar.repository.page.ResponsePageImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class UserRepositoryImpl implements UserCompositeRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ResponsePage<UserResponse> findAllByFilter(UserFilter filter) {
        final boolean sorted = filter.formPageable().getSort().isSorted();
        final boolean hasSearch = StringUtils.isNotBlank(filter.getSearch());


        StringBuilder sql = new StringBuilder("select t from User t ");


        String countSql = sql.toString().replace("select t", "select count(t)");


        TypedQuery<User> query = entityManager.createQuery(sql.toString(), User.class);
        TypedQuery<Long> countQuery = entityManager.createQuery(countSql, Long.class);

        Long totalElements = countQuery.getSingleResult();

        ResponsePageImpl<UserResponse> response = new ResponsePageImpl<>();
        response.setElements(new ArrayList<>());
        response.setTotalElements(totalElements);

        return new ResponsePageImpl<>();
    }
}
