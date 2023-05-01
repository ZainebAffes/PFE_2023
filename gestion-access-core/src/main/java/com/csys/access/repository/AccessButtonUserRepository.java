package com.csys.access.repository;

import com.csys.access.domain.AccessButtonUser;
import com.csys.access.domain.AccessButtonUserPK;
import com.querydsl.core.types.Predicate;
import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the AccessButtonUser entity.
 */
@Repository
public interface AccessButtonUserRepository extends JpaRepository<AccessButtonUser, AccessButtonUserPK>, QueryDslPredicateExecutor<AccessButtonUser> {

    @Override
    @EntityGraph(value = "AccessButtonUser.button", type = EntityGraph.EntityGraphType.LOAD)
    public List<AccessButtonUser> findAll(Predicate predicate);
}
