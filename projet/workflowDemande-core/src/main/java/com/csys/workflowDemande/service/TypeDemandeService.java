package com.csys.workflowDemande.service;

import com.csys.workflowDemande.domain.TypeDemande;
import com.csys.workflowDemande.dto.TypeDemandeDTO;
import com.csys.workflowDemande.factory.TypeDemandeFactory;
import com.csys.workflowDemande.repository.TypeDemandeRepository;
import com.google.common.base.Preconditions;
import java.lang.String;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TypeDemandeService {
  private final Logger log = LoggerFactory.getLogger(TypeDemandeService.class);

  private final TypeDemandeRepository typedemandeRepository;

  public TypeDemandeService(TypeDemandeRepository typedemandeRepository) {
    this.typedemandeRepository=typedemandeRepository;
  }

  public TypeDemandeDTO save(TypeDemandeDTO typedemandeDTO) {
    log.debug("Request to save TypeDemande: {}",typedemandeDTO);
    TypeDemande typedemande = TypeDemandeFactory.typedemandeDTOToTypeDemande(typedemandeDTO);
    typedemande = typedemandeRepository.save(typedemande);
    TypeDemandeDTO resultDTO = TypeDemandeFactory.typedemandeToTypeDemandeDTO(typedemande);
    return resultDTO;
  }

  public TypeDemandeDTO update(TypeDemandeDTO typedemandeDTO) {
    log.debug("Request to update TypeDemande: {}",typedemandeDTO);
//    TypeDemande inBase= typedemandeRepository.findOne(typedemandeDTO.getCodeTypeDemande());
 //   Preconditions.checkArgument(inBase != null, "TypeDemande does not exist");
    TypeDemandeDTO result= save(typedemandeDTO);
    return result;
  }

//  @Transactional(
//      readOnly = true
//  )
//  public TypeDemandeDTO findOne(String id) {
//    log.debug("Request to get TypeDemande: {}",id);
//    TypeDemande typedemande= typedemandeRepository.findOne(id);
//    Preconditions.checkArgument(typedemande != null, "TypeDemande does not exist");
//    TypeDemandeDTO dto = TypeDemandeFactory.typedemandeToTypeDemandeDTO(typedemande);
//    return dto;
//  }
//
//  @Transactional(
//      readOnly = true
//  )
//  public TypeDemande findTypeDemande(String id) {
//    log.debug("Request to get TypeDemande: {}",id);
////    TypeDemande typedemande= typedemandeRepository.findOne(id);
//    //Preconditions.checkArgument(typedemande != null, "TypeDemande does not exist");
//    //return typedemande;
//  }

  @Transactional(
      readOnly = true
  )
  public Collection<TypeDemandeDTO> findAll() {
    log.debug("Request to get All TypeDemandes");
    Collection<TypeDemande> result= typedemandeRepository.findAll();
    return TypeDemandeFactory.typedemandeToTypeDemandeDTOs(result);
  }

//  public void delete(String id) {
//    log.debug("Request to delete TypeDemande: {}",id);
//    typedemandeRepository.delete(id);
//  }
}

