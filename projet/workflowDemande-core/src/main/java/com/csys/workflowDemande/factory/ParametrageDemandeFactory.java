package com.csys.workflowDemande.factory;

import com.csys.workflowDemande.domain.ParametrageDemande;
import com.csys.workflowDemande.dto.ParametrageDemandeDTO;
import java.util.ArrayList;
import java.util.List;

public class ParametrageDemandeFactory {
  public static ParametrageDemandeDTO parametragedemandeToParametrageDemandeDTO(ParametrageDemande parametragedemande) {
    ParametrageDemandeDTO parametragedemandeDTO=new ParametrageDemandeDTO();
    parametragedemandeDTO.setCode(parametragedemande.getCode());
    parametragedemandeDTO.setDesignation(parametragedemande.getDesignation());
    parametragedemandeDTO.setCodeParametrageEtiquette(parametragedemande.getCodeParametrageEtiquette());
    parametragedemandeDTO.setCodeTypeDemande(parametragedemande.getCodeTypeDemande());
    return parametragedemandeDTO;
  }

  public static ParametrageDemande parametragedemandeDTOToParametrageDemande(ParametrageDemandeDTO parametragedemandeDTO) {
    ParametrageDemande parametragedemande=new ParametrageDemande();
    parametragedemande.setCode(parametragedemandeDTO.getCode());
    parametragedemande.setDesignation(parametragedemandeDTO.getDesignation());
    parametragedemande.setCodeParametrageEtiquette(parametragedemandeDTO.getCodeParametrageEtiquette());
    parametragedemande.setCodeTypeDemande(parametragedemandeDTO.getCodeTypeDemande());
    return parametragedemande;
  }

  public static List<ParametrageDemandeDTO> parametragedemandeToParametrageDemandeDTOs(List<ParametrageDemande> parametragedemandes) {
    List<ParametrageDemandeDTO> parametragedemandesDTO=new ArrayList<>();
    parametragedemandes.forEach(x -> {
      parametragedemandesDTO.add(parametragedemandeToParametrageDemandeDTO(x));
    } );
    return parametragedemandesDTO;
  }
}

