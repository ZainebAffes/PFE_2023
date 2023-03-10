package com.csys.workflowDemande.service;

import com.csys.workflowDemande.domain.TypeDemande;
import com.csys.workflowDemande.dto.TypeDemandeDTO;
import com.csys.workflowDemande.factory.TypeDemandeFactory;
import com.csys.workflowDemande.repository.TypeDemandeRepository;
import java.lang.String;
<<<<<<< HEAD
import java.util.Collection;
=======
import java.util.List;
>>>>>>> ff2732035e0b77f360ecb88a31fcc8b4a76a883f
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.csys.workflowDemande.util.Preconditions;


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
    TypeDemande inBase= typedemandeRepository.findById(typedemandeDTO.getCodeTypeDemande()).get();
    Preconditions.checkBusinessLogique(inBase != null, "TypeDemande does not exist");
    TypeDemandeDTO result= save(typedemandeDTO);
    return result;
  }

  @Transactional(
      readOnly = true
  )
  public TypeDemandeDTO findOne(String id) {
    log.debug("Request to get TypeDemande: {}",id);
      Optional<TypeDemande> typedemande= typedemandeRepository.findById(id);
    Preconditions.checkBusinessLogique(typedemande != null, "TypeDemande does not exist");
    TypeDemandeDTO dto = TypeDemandeFactory.typedemandeToTypeDemandeDTO(typedemande.get());
    return dto;
  }
<<<<<<< HEAD

  @Transactional(
      readOnly = true
  )
  public TypeDemande findTypeDemande(String id) {
    log.debug("Request to get TypeDemande: {}",id);
     Optional<TypeDemande> typedemande= typedemandeRepository.findById(id);
    Preconditions.checkBusinessLogique(typedemande != null, "TypeDemande does not exist");
    return typedemande.get();
  }
=======
>>>>>>> ff2732035e0b77f360ecb88a31fcc8b4a76a883f

  @Transactional(
      readOnly = true
  )
  public TypeDemande findTypeDemande(String id) {
    log.debug("Request to get TypeDemande: {}",id);
     Optional<TypeDemande> typedemande= typedemandeRepository.findById(id);
    Preconditions.checkBusinessLogique(typedemande != null, "TypeDemande does not exist");
    return typedemande.get();
  }

  @Transactional(
      readOnly = true
  )
  public List<TypeDemandeDTO> findAll() {
    log.debug("Request to get AllfindAll TypeDemandes");
    List<TypeDemande> result= typedemandeRepository.findAll();
    return TypeDemandeFactory.typedemandeToTypeDemandeDTOs(result);
  }

  public void delete(String id) { log.debug("Request to delete type-demande devis: {}", id);
        TypeDemande inBase = findTypeDemande(id);
        Preconditions.checkBusinessLogique(inBase != null, "type-demande.NotFound");
        
    typedemandeRepository.deleteById(id);
  }
}

