package com.csys.workflowDemande.repository;

import com.csys.workflowDemande.domain.Etat;

import java.lang.String;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtatRepository extends JpaRepository<Etat, String> {

  
}

