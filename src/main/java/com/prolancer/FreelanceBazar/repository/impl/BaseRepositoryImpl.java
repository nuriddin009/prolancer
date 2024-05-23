package com.prolancer.FreelanceBazar.repository.impl;

import com.prolancer.FreelanceBazar.entity.template.BaseEntity;
import com.prolancer.FreelanceBazar.repository.BaseRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.util.List;
import java.util.UUID;

public class BaseRepositoryImpl<T extends BaseEntity> extends SimpleJpaRepository<T, UUID> implements BaseRepository<T> {

    private final Specification<T> isNotDeletedSpecification = (root, query, cb) -> cb.equal(root.get("deleted"), false);

    public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
    }

    @Transactional
    @Override
    public T findByIdAndDeletedFalse(UUID id) {
        T entity = findById(id).orElse(null);
        return (entity != null && !entity.isDeleted()) ? entity : null;
    }

    @Transactional
    @Override
    public T trash(UUID id) {
        T entity = findById(id).orElse(null);
        if (entity != null) {
            entity.setDeleted(true);
            save(entity);
        }
        return entity;
    }

    @Override
    public List<T> findAllNotDeleted() {
        return findAll(isNotDeletedSpecification);
    }

    @Override
    public Page<T> findAllNotDeleted(Pageable pageable) {
        return findAll(isNotDeletedSpecification, pageable);
    }

    @Override
    public List<T> trashList(List<UUID> ids) {
        return ids.stream().map(this::trash).toList();
    }
}