package com.csys.workflowDemande.factory;

import com.csys.workflowDemande.domain.Etat;
import com.csys.workflowDemande.dto.EtatDTO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EtatFactory {
  public static EtatDTO etatToEtatDTO(Etat etat) {
    EtatDTO etatDTO=new EtatDTO();
    etatDTO.setCode(etat.getCode());
    etatDTO.setDesignation(etat.getDesignation());
    etatDTO.setLogo(etat.getLogo());
    return etatDTO;
  }

  public static Etat etatDTOToEtat(EtatDTO etatDTO) {
    Etat etat=new Etat();
    etat.setCode(etatDTO.getCode());
    etat.setDesignation(etatDTO.getDesignation());
    etat.setLogo(etatDTO.getLogo());
    return etat;
  }

  public static Collection<EtatDTO> etatToEtatDTOs(Collection<Etat> etats) {
    List<EtatDTO> etatsDTO=new ArrayList<>();
    etats.forEach(x -> {
      etatsDTO.add(etatToEtatDTO(x));
    } );
    return etatsDTO;
  }
}

