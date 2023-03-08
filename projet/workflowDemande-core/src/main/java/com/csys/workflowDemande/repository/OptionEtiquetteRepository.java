package com.csys.workflowDemande.repository;

import com.csys.workflowDemande.domain.OptionEtiquette;
import java.lang.Boolean;
import java.lang.Integer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionEtiquetteRepository extends JpaRepository<OptionEtiquette, Integer> {
 // List<OptionEtiquette> findByActif(Boolean actif);

    

    public OptionEtiquette findByCodeOption(Integer id);
}

