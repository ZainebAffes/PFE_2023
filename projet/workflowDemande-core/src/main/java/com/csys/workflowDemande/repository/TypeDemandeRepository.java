package com.csys.workflowDemande.repository;

import com.csys.workflowDemande.domain.TypeDemande;
import java.lang.String;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface TypeDemandeRepository extends JpaRepository<TypeDemande, String> {

    public TypeDemande findByCodeTypeDemande(String codeTypeDemande);

   // public void delete(String id);
}

