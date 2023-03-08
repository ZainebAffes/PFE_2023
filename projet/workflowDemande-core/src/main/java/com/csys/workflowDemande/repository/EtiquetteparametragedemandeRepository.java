package com.csys.workflowDemande.repository;

import com.csys.workflowDemande.domain.Etiquetteparametragedemande;
import java.lang.Boolean;
import java.lang.Integer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtiquetteparametragedemandeRepository extends JpaRepository<Etiquetteparametragedemande, Integer> {
  //List<Etiquetteparametragedemande> findByActif(Boolean actif);

    public Etiquetteparametragedemande findByCode(Integer code);

 
}

