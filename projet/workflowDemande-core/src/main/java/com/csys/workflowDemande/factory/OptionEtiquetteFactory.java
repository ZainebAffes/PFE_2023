package com.csys.workflowDemande.factory;

import com.csys.workflowDemande.domain.OptionEtiquette;
import com.csys.workflowDemande.dto.OptionEtiquetteDTO;
import java.util.ArrayList;

import java.util.List;

public class OptionEtiquetteFactory {
  public static OptionEtiquetteDTO optionetiquetteToOptionEtiquetteDTO(OptionEtiquette optionetiquette) {
    OptionEtiquetteDTO optionetiquetteDTO=new OptionEtiquetteDTO();
    optionetiquetteDTO.setCodeOption(optionetiquette.getCodeOption());
    optionetiquetteDTO.setChoix(optionetiquette.getChoix());
    optionetiquetteDTO.setEtiquetteparametragedemandeList(optionetiquette.getEtiquetteparametragedemandeList());
    return optionetiquetteDTO;
  }

  public static OptionEtiquette optionetiquetteDTOToOptionEtiquette(OptionEtiquetteDTO optionetiquetteDTO) {
    OptionEtiquette optionetiquette=new OptionEtiquette();
    optionetiquette.setCodeOption(optionetiquetteDTO.getCodeOption());
    optionetiquette.setChoix(optionetiquetteDTO.getChoix());
    optionetiquette.setEtiquetteparametragedemandeList(optionetiquetteDTO.getEtiquetteparametragedemandeList());
    return optionetiquette;
  }

  public static List<OptionEtiquetteDTO> optionetiquetteToOptionEtiquetteDTOs(List<OptionEtiquette> optionetiquettes) {
    List<OptionEtiquetteDTO> optionetiquettesDTO=new ArrayList<>();
    optionetiquettes.forEach(x -> {
      optionetiquettesDTO.add(optionetiquetteToOptionEtiquetteDTO(x));
    } );
    return optionetiquettesDTO;
  }
}

