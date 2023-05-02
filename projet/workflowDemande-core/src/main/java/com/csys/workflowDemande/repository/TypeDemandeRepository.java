package com.csys.workflowDemande.repository;

import com.csys.workflowDemande.domain.TypeDemande;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeDemandeRepository extends JpaRepository<TypeDemande, String>, QuerydslPredicateExecutor<TypeDemande> {

    public TypeDemande findByCodeTypeDemande(String codeTypeDemande);

    public List<TypeDemande> findByDescription(String description);
    // public void delete(String id);
}
