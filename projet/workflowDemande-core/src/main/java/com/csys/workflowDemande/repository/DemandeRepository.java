package com.csys.workflowDemande.repository;

import com.csys.workflowDemande.domain.Demande;
import java.lang.String;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandeRepository extends JpaRepository<Demande, Integer>, QuerydslPredicateExecutor<Demande> {
//  Collection<Demande> findByActif(Boolean actif);

    public List<Demande> findByDesignation(String designation);

    public Demande findByCode(Integer id);
}
