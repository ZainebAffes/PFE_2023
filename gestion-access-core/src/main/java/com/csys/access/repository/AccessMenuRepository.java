package com.csys.access.repository;

import com.csys.access.domain.AccessMenu;
import com.csys.access.domain.AccessMenuPK;
import com.csys.access.domain.AccessMenuUser;
import com.querydsl.core.types.Predicate;
import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the AccessMenu entity.
 */
@Repository
public interface AccessMenuRepository extends JpaRepository<AccessMenu, AccessMenuPK>, QueryDslPredicateExecutor<AccessMenu> {

    @Override
    @EntityGraph(value = "AccessMenu.menu", type = EntityGraph.EntityGraphType.LOAD)
    public List<AccessMenu> findAll(Predicate predicate);
}
