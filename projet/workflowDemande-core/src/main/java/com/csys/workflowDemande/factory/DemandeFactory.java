package com.csys.workflowDemande.factory;

import com.csys.workflowDemande.domain.Demande;
import com.csys.workflowDemande.domain.Champs;
import com.csys.workflowDemande.dto.DemandeDTO;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DemandeFactory {

    public static DemandeDTO demandeToDemandeDTO(Demande demande) {
        DemandeDTO demandeDTO = new DemandeDTO();
        demandeDTO.setCode(demande.getCode());
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
            demandeDTO.setChampsDTOs(ChampsFactory.listdeschampsToChampsDTOs(demande.getChampses()));
        

// demandeDTO.setCodeParametrageDemande(demande.getCodeParametrageDemande());
        return demandeDTO;
    }

    public static Demande demandeDTOToDemande(DemandeDTO demandeDTO, Demande demande) {
        if (demande == null) {
            demande = new Demande();
        }
        demande.setDesignation(demandeDTO.getDesignation());
        demande.setDateCreation(LocalDateTime.now());
        demande.setCodeParametrageDemandes(demandeDTO.getCodeParametrage());
        demande.setIdEmployes(demandeDTO.getIdEmployes());

        List<Champs> champsLists = new ArrayList<>();
        demandeDTO.getChampsDTOs().forEach(x -> {
            Champs champs = ChampsFactory.champsDTOToChamps(x);

            champsLists.add(champs);
        });
        if (demande.getChampses() != null) {
            demande.getChampses().clear();
            demande.getChampses().addAll(champsLists);
        } else {
            demande.setChampses(champsLists);
        }
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
