package com.csys.access.repository;

import com.csys.access.domain.Button;
import com.csys.access.domain.ButtonPK;
import com.csys.access.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Button entity.
 */
@Repository
public interface ButtonRepository extends JpaRepository<Button, ButtonPK>  , QueryDslPredicateExecutor<Button>{
}

