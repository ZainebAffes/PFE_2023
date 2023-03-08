package com.csys.workflowDemande.repository;

import com.csys.workflowDemande.domain.ParametrageDemande;
import java.lang.Boolean;
import java.lang.Integer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParametrageDemandeRepository extends JpaRepository<ParametrageDemande, Integer> {
 // List<ParametrageDemande> findByActif(Boolean actif);

    

    public ParametrageDemande findByCode(Integer id);
}

