package com.csys.workflowDemande.factory;

import com.csys.workflowDemande.domain.Demande;
import com.csys.workflowDemande.dto.DemandeDTO;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DemandeFactory {

    public static DemandeDTO demandeToDemandeDTO(Demande demande) {
        DemandeDTO demandeDTO = new DemandeDTO();
        demandeDTO.setNumeroDemande(demande.getNumeroDemande());
        demandeDTO.setDesignation(demande.getDesignation());
        demandeDTO.setDateCreation(demande.getDateCreation() != null ? demande.getDateCreation() : null);
        if (demande.getIdEmploye() != null) {
            demandeDTO.setNomEmploye(demande.getIdEmploye().getNom() + " " + demande.getIdEmploye().getPrenom());
        }
        if (demande.getCodeParametrageDemande() != null) {
            demandeDTO.setDesParametrageDemande(demande.getCodeParametrageDemande().getDesignation());
            demandeDTO.setTypeDemande(demande.getCodeParametrageDemande().getTypeDemande().getDescription());
        }
        if (demande.getEtat() != null) {
            demandeDTO.setLogoEtat(demande.getEtat().getLogo());
            demandeDTO.setIdEtat(demande.getEtat().getCode());
        }
       // demandeDTO.setCodeParametrageDemande(demande.getCodeParametrageDemande());
        return demandeDTO;
    }

    public static Demande demandeDTOToDemande(DemandeDTO demandeDTO) {
        Demande demande = new Demande();
        demande.setNumeroDemande(demandeDTO.getNumeroDemande());
        demande.setDesignation(demandeDTO.getDesignation());
        demande.setDateCreation(LocalDateTime.now());
        demande.setCodeParametrageDemande(demandeDTO.getCodeParametrageDemande());
        return demande;
    }

    public static Collection<DemandeDTO> demandeToDemandeDTOs(Collection<Demande> demandes) {
        List<DemandeDTO> demandesDTO = new ArrayList<>();
        demandes.forEach(x -> {
            demandesDTO.add(demandeToDemandeDTO(x));
        });
        return demandesDTO;
    }
}
