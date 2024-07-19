package com.prolancer.FreelanceBazar.repository.impl;

import com.prolancer.FreelanceBazar.entity.User;
import com.prolancer.FreelanceBazar.filter.UserFilter;
import com.prolancer.FreelanceBazar.mapper.UserMapper;
import com.prolancer.FreelanceBazar.payload.response.UserResponse;
import com.prolancer.FreelanceBazar.repository.custom.UserCompositeRepository;
import com.prolancer.FreelanceBazar.repository.page.ResponsePage;
import com.prolancer.FreelanceBazar.repository.page.ResponsePageImpl;
import com.prolancer.FreelanceBazar.utils.QueryUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserCompositeRepository {
    @PersistenceContext
    private EntityManager entityManager;
    private final UserMapper userMapper;

    @Override
    public ResponsePage<UserResponse> findAllByFilter(UserFilter filter) {
        final boolean sorted = filter.formPageable().getSort().isSorted();
        final boolean hasSearch = StringUtils.isNotBlank(filter.getSearch());


        StringBuilder sql = new StringBuilder("select t from User t ");

        sql.append(" where t.deleted=false ");
        if (hasSearch) {
            sql.append(" and (lower(t.firstname) like :searchKey ");
            sql.append(" or lower(t.lastname) like :searchKey) ");
        }
        if (filter.getStatus() != null) {
            sql.append(" and t.status=:status ");
        }

        String countSql = sql.toString().replace("select t", "select count(t)");

        QueryUtils.appendQuery(sorted, filter, sql);

        TypedQuery<User> query = entityManager.createQuery(sql.toString(), User.class);
        TypedQuery<Long> countQuery = entityManager.createQuery(countSql, Long.class);

        if (hasSearch) {
            query.setParameter("searchKey", filter.getSearchForQuery());
            countQuery.setParameter("searchKey", filter.getSearchForQuery());
        }

        if (filter.getStatus() != null) {
            query.setParameter("status", filter.getStatus());
        }

        Long totalElements = countQuery.getSingleResult();

        List<UserResponse> responseData = query.getResultList().stream()
                .map(userMapper::toResponse).toList();

        ResponsePageImpl<UserResponse> response = new ResponsePageImpl<>();
        response.setElements(responseData);
        response.setTotalElements(totalElements);

        return response;
    }
}
