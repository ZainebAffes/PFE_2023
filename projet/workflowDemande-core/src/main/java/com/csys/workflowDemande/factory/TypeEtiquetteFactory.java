package com.csys.workflowDemande.factory;

import com.csys.workflowDemande.domain.TypeEtiquette;
import com.csys.workflowDemande.dto.TypeEtiquetteDTO;
import java.util.ArrayList;
import java.util.List;

public class TypeEtiquetteFactory {
  public static TypeEtiquetteDTO typeetiquetteToTypeEtiquetteDTO(TypeEtiquette typeetiquette) {
    TypeEtiquetteDTO typeetiquetteDTO=new TypeEtiquetteDTO();
    typeetiquetteDTO.setCode(typeetiquette.getCode());
    typeetiquetteDTO.setType(typeetiquette.getType());
    typeetiquetteDTO.setLogo(typeetiquette.getLogo());
     return typeetiquetteDTO;
  }

  public static TypeEtiquette typeetiquetteDTOToTypeEtiquette(TypeEtiquetteDTO typeetiquetteDTO) {
    TypeEtiquette typeetiquette=new TypeEtiquette();
    typeetiquette.setCode(typeetiquetteDTO.getCode());
    typeetiquette.setType(typeetiquetteDTO.getType());
    typeetiquette.setLogo(typeetiquetteDTO.getLogo());
    return typeetiquette;
  }

  public static List<TypeEtiquetteDTO> typeetiquetteToTypeEtiquetteDTOs(List<TypeEtiquette> typeetiquettes) {
    List<TypeEtiquetteDTO> typeetiquettesDTO=new ArrayList<>();
    typeetiquettes.forEach(x -> {
      typeetiquettesDTO.add(typeetiquetteToTypeEtiquetteDTO(x));
    } );
    return typeetiquettesDTO;
  }
}

