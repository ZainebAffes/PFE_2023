package com.csys.workflowDemande.factory;

import com.csys.workflowDemande.domain.Demande;
import com.csys.workflowDemande.dto.DemandeDTO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DemandeFactory {
  public static DemandeDTO demandeToDemandeDTO(Demande demande) {
    DemandeDTO demandeDTO=new DemandeDTO();
    demandeDTO.setNumeroDemande(demande.getNumeroDemande());
    demandeDTO.setDesignation(demande.getDesignation());
    demandeDTO.setDateCreation(demande.getDateCreation());
    demandeDTO.setTypeDemande(demande.getTypeDemande());
//    demandeDTO.setIdEmploye(demande.getIdEmploye());
    demandeDTO.setCodeParametrageDemande(demande.getCodeParametrageDemande());
    return demandeDTO;
  }

  public static Demande demandeDTOToDemande(DemandeDTO demandeDTO) {
    Demande demande=new Demande();
    demande.setNumeroDemande(demandeDTO.getNumeroDemande());
    demande.setDesignation(demandeDTO.getDesignation());
    demande.setDateCreation(demandeDTO.getDateCreation());
    demande.setTypeDemande(demandeDTO.getTypeDemande());
//    demande.setIdEmploye(demandeDTO.getIdEmploye());
    demande.setCodeParametrageDemande(demandeDTO.getCodeParametrageDemande());
    return demande;
  }

  public static Collection<DemandeDTO> demandeToDemandeDTOs(Collection<Demande> demandes) {
    List<DemandeDTO> demandesDTO=new ArrayList<>();
    demandes.forEach(x -> {
      demandesDTO.add(demandeToDemandeDTO(x));
    } );
    return demandesDTO;
  }
}

