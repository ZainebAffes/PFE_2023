package com.csys.workflowDemande.repository;

import com.csys.workflowDemande.domain.TypeEtiquette;
//import java.lang.Boolean;
//import java.lang.Integer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeEtiquetteRepository extends JpaRepository<TypeEtiquette, Integer> {
 // List<TypeEtiquette> findByActif(Boolean actif);

    public TypeEtiquette findByCode(Integer code);

    //public void delete(Integer id);
}

