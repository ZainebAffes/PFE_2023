package com.csys.workflowDemande.repository;

import com.csys.workflowDemande.domain.Demande;
import java.lang.Boolean;
import java.lang.String;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandeRepository extends JpaRepository<Demande, String> {
//  Collection<Demande> findByActif(Boolean actif);

    public Demande findByNumeroDemande(String id);
}

