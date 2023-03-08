package com.csys.workflowDemande.factory;

import com.csys.workflowDemande.domain.Etiquetteparametragedemande;
import com.csys.workflowDemande.dto.EtiquetteparametragedemandeDTO;
import java.util.ArrayList;

import java.util.List;

public class EtiquetteparametragedemandeFactory {
  public static EtiquetteparametragedemandeDTO etiquetteparametragedemandeToEtiquetteparametragedemandeDTO(Etiquetteparametragedemande etiquetteparametragedemande) {
    EtiquetteparametragedemandeDTO etiquetteparametragedemandeDTO=new EtiquetteparametragedemandeDTO();
    etiquetteparametragedemandeDTO.setCode(etiquetteparametragedemande.getCode());
    etiquetteparametragedemandeDTO.setDescription(etiquetteparametragedemande.getDescription());
    etiquetteparametragedemandeDTO.setMin(etiquetteparametragedemande.getMin());
    etiquetteparametragedemandeDTO.setMax(etiquetteparametragedemande.getMax());
    etiquetteparametragedemandeDTO.setIsRequired(etiquetteparametragedemande.getIsRequired());
    etiquetteparametragedemandeDTO.setPosition(etiquetteparametragedemande.getPosition());
    etiquetteparametragedemandeDTO.setDefultValue(etiquetteparametragedemande.getDefultValue());
    etiquetteparametragedemandeDTO.setVisible(etiquetteparametragedemande.getVisible());
    etiquetteparametragedemandeDTO.setMultiple(etiquetteparametragedemande.getMultiple());
    etiquetteparametragedemandeDTO.setOptionEtiquette(etiquetteparametragedemande.getOptionEtiquette());
    etiquetteparametragedemandeDTO.setCodeTypeEtiquette(etiquetteparametragedemande.getCodeTypeEtiquette());
    etiquetteparametragedemandeDTO.setParametrageDemandeList(etiquetteparametragedemande.getParametrageDemandeList());
    return etiquetteparametragedemandeDTO;
  }

  public static Etiquetteparametragedemande etiquetteparametragedemandeDTOToEtiquetteparametragedemande(EtiquetteparametragedemandeDTO etiquetteparametragedemandeDTO) {
    Etiquetteparametragedemande etiquetteparametragedemande=new Etiquetteparametragedemande();
    etiquetteparametragedemande.setCode(etiquetteparametragedemandeDTO.getCode());
    etiquetteparametragedemande.setDescription(etiquetteparametragedemandeDTO.getDescription());
    etiquetteparametragedemande.setMin(etiquetteparametragedemandeDTO.getMin());
    etiquetteparametragedemande.setMax(etiquetteparametragedemandeDTO.getMax());
    etiquetteparametragedemande.setIsRequired(etiquetteparametragedemandeDTO.getIsRequired());
    etiquetteparametragedemande.setPosition(etiquetteparametragedemandeDTO.getPosition());
    etiquetteparametragedemande.setDefultValue(etiquetteparametragedemandeDTO.getDefultValue());
    etiquetteparametragedemande.setVisible(etiquetteparametragedemandeDTO.getVisible());
    etiquetteparametragedemande.setMultiple(etiquetteparametragedemandeDTO.getMultiple());
    etiquetteparametragedemande.setOptionEtiquette(etiquetteparametragedemandeDTO.getOptionEtiquette());
    etiquetteparametragedemande.setCodeTypeEtiquette(etiquetteparametragedemandeDTO.getCodeTypeEtiquette());
    etiquetteparametragedemande.setParametrageDemandeList(etiquetteparametragedemandeDTO.getParametrageDemandeList());
    return etiquetteparametragedemande;
  }

  public static List<EtiquetteparametragedemandeDTO> etiquetteparametragedemandeToEtiquetteparametragedemandeDTOs(List<Etiquetteparametragedemande> etiquetteparametragedemandes) {
    List<EtiquetteparametragedemandeDTO> etiquetteparametragedemandesDTO=new ArrayList<>();
    etiquetteparametragedemandes.forEach(x -> {
      etiquetteparametragedemandesDTO.add(etiquetteparametragedemandeToEtiquetteparametragedemandeDTO(x));
    } );
    return etiquetteparametragedemandesDTO;
  }
}

