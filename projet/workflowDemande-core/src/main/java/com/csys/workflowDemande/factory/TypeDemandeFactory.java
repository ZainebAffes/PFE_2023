package com.csys.workflowDemande.factory;

import com.csys.workflowDemande.domain.TypeDemande;
import com.csys.workflowDemande.dto.TypeDemandeDTO;
import java.util.ArrayList;
import java.util.List;

public class TypeDemandeFactory {
  public static TypeDemandeDTO typedemandeToTypeDemandeDTO(TypeDemande typedemande) {
    TypeDemandeDTO typedemandeDTO=new TypeDemandeDTO();
    typedemandeDTO.setCodeTypeDemande(typedemande.getCodeTypeDemande());
    typedemandeDTO.setDescription(typedemande.getDescription());
    typedemandeDTO.setNom(typedemande.getNom());
    return typedemandeDTO;
  }

  public static TypeDemande typedemandeDTOToTypeDemande(TypeDemandeDTO typedemandeDTO) {
    TypeDemande typedemande=new TypeDemande();
    typedemande.setCodeTypeDemande(typedemandeDTO.getCodeTypeDemande());
    typedemande.setDescription(typedemandeDTO.getDescription());
    typedemande.setNom(typedemandeDTO.getNom());
    return typedemande;
  }

  public static List<TypeDemandeDTO> typedemandeToTypeDemandeDTOs(List<TypeDemande> typedemandes) {
    List<TypeDemandeDTO> typedemandesDTO=new ArrayList<>();
    typedemandes.forEach(x -> {
      typedemandesDTO.add(typedemandeToTypeDemandeDTO(x));
    } );
    return typedemandesDTO;
  }
}

