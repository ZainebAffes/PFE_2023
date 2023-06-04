package com.csys.workflowDemande.repository;

import com.csys.workflowDemande.domain.Demande;
import com.csys.workflowDemande.domain.Champs;
import java.lang.Integer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ChampsRepository extends JpaRepository<Champs, Integer>, QuerydslPredicateExecutor<Champs> {

}

