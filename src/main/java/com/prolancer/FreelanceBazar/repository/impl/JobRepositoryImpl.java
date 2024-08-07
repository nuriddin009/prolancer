package com.prolancer.FreelanceBazar.repository.impl;

import com.prolancer.FreelanceBazar.entity.JobEntity;
import com.prolancer.FreelanceBazar.filter.JobFilter;
import com.prolancer.FreelanceBazar.repository.custom.JobCompositeRepository;
import com.prolancer.FreelanceBazar.utils.QueryUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

@Repository
public class JobRepositoryImpl implements JobCompositeRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<JobEntity> findAllByFilter(JobFilter filter) {
        final boolean hasSearch = StringUtils.isNotBlank(filter.getSearch());
        final boolean isSorted = filter.formPageable().getSort().isSorted();

        StringBuilder sql = new StringBuilder("select t from JobEntity t ");

        sql.append(" inner join lower(t.jobSkills.skillName) like :searchKey ");


        if (hasSearch) {
            sql.append(" where t.deleted=false ");
        }


        String countSql = sql.toString().replace("select t", "select count(t)");

        QueryUtils.appendQuery(isSorted, filter, sql);

        TypedQuery<JobEntity> query = entityManager.createQuery(sql.toString(), JobEntity.class);
        TypedQuery<Long> countQuery = entityManager.createQuery(countSql, Long.class);

        if (hasSearch) {
            query.setParameter("searchKey", filter.getSearchForQuery());
            countQuery.setParameter("searchKey", filter.getSearchForQuery());
        }

        return new PageImpl<>(query.getResultList(), filter.formPageable(), countQuery.getSingleResult());
    }
}
