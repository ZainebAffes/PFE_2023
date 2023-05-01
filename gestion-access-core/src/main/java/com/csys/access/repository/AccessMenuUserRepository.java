package com.csys.access.repository;

import com.csys.access.domain.AccessMenuUser;
import com.csys.access.domain.AccessMenuUserPK;
import com.querydsl.core.types.Predicate;
import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the AccessMenuUser entity.
 */
@Repository
public interface AccessMenuUserRepository extends JpaRepository<AccessMenuUser, AccessMenuUserPK>, QueryDslPredicateExecutor<AccessMenuUser> {
    @Override
    @EntityGraph(value = "AccessMenuUser.menu", type = EntityGraph.EntityGraphType.LOAD)
    public List<AccessMenuUser> findAll(Predicate predicate);
}

