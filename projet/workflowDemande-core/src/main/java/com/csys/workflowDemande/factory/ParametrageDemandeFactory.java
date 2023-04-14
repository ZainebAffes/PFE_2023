package com.csys.workflowDemande.factory;

import com.csys.workflowDemande.domain.Etiquetteparametragedemande;
import com.csys.workflowDemande.domain.ParametrageDemande;
import com.csys.workflowDemande.dto.ParametrageDemandeDTO;
import java.util.ArrayList;
import java.util.List;

public class ParametrageDemandeFactory {

    public static ParametrageDemandeDTO parametragedemandeToParametrageDemandeDTO(ParametrageDemande parametragedemande) {
        ParametrageDemandeDTO parametragedemandeDTO = new ParametrageDemandeDTO();
        parametragedemandeDTO.setCode(parametragedemande.getCode());
        parametragedemandeDTO.setDesignation(parametragedemande.getDesignation());
        parametragedemandeDTO.setEtiquetteparametragedemandeDTOs(EtiquetteparametragedemandeFactory.etiquetteparametragedemandeToEtiquetteparametragedemandeDTOs(parametragedemande.getEtiquetteparametragedemandes()));
        parametragedemandeDTO.setCodeTypeDemande(parametragedemande.getCodeTypeDemande());
        parametragedemandeDTO.setDescriptionTypeDemande(parametragedemande.getTypeDemande().getDescription());
        return parametragedemandeDTO;
    }

    public static ParametrageDemande parametragedemandeDTOToParametrageDemande(ParametrageDemandeDTO parametragedemandeDTO) {
        ParametrageDemande parametragedemande = new ParametrageDemande();
        parametragedemande.setCode(parametragedemandeDTO.getCode());
        parametragedemande.setDesignation(parametragedemandeDTO.getDesignation());
        parametragedemande.setCodeTypeDemande(parametragedemandeDTO.getCodeTypeDemande());
        List<Etiquetteparametragedemande> etiquetteparametragedemandesLists = new ArrayList<>();
        parametragedemandeDTO.getEtiquetteparametragedemandeDTOs().forEach(x -> {
            x.setCode(parametragedemandeDTO.getCode());
            Etiquetteparametragedemande etiquetteparametragedemande = EtiquetteparametragedemandeFactory.etiquetteparametragedemandeDTOToEtiquetteparametragedemande(x);
            etiquetteparametragedemandesLists.add(etiquetteparametragedemande);
        });
        if (parametragedemande.getEtiquetteparametragedemandes() != null) {
            parametragedemande.getEtiquetteparametragedemandes().clear();
            parametragedemande.getEtiquetteparametragedemandes().addAll(etiquetteparametragedemandesLists);
        } else {
            parametragedemande.setEtiquetteparametragedemandes(etiquetteparametragedemandesLists);
        }
        return parametragedemande;
    }

    public static List<ParametrageDemandeDTO> parametragedemandeToParametrageDemandeDTOs(List<ParametrageDemande> parametragedemandes) {
        List<ParametrageDemandeDTO> parametragedemandesDTO = new ArrayList<>();
        parametragedemandes.forEach(x -> {
            parametragedemandesDTO.add(parametragedemandeToParametrageDemandeDTO(x));
        });
        return parametragedemandesDTO;
    }
}
