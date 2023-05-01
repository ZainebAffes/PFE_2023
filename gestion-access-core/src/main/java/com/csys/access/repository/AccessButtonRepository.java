package com.csys.access.repository;

import com.csys.access.domain.AccessButton;
import com.csys.access.domain.AccessButtonUserPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the AccessButton entity.
 */
@Repository
public interface AccessButtonRepository extends JpaRepository<AccessButton, AccessButtonUserPK>, QueryDslPredicateExecutor<AccessButton> {
}

