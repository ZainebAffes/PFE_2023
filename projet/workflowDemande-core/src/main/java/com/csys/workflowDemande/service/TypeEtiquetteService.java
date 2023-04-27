package com.csys.workflowDemande.service;

import com.csys.workflowDemande.domain.TypeEtiquette;
import com.csys.workflowDemande.dto.TypeEtiquetteDTO;
import com.csys.workflowDemande.factory.TypeEtiquetteFactory;
import com.csys.workflowDemande.repository.TypeEtiquetteRepository;
import com.google.common.base.Preconditions;
import java.lang.Integer;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TypeEtiquetteService {
  private final Logger log = LoggerFactory.getLogger(TypeEtiquetteService.class);

  private final TypeEtiquetteRepository typeetiquetteRepository;

  public TypeEtiquetteService(TypeEtiquetteRepository typeetiquetteRepository) {
    this.typeetiquetteRepository=typeetiquetteRepository;
  }

  public TypeEtiquetteDTO save(TypeEtiquetteDTO typeetiquetteDTO) {
    log.debug("Request to save TypeEtiquette: {}",typeetiquetteDTO);
    TypeEtiquette typeetiquette = TypeEtiquetteFactory.typeetiquetteDTOToTypeEtiquette(typeetiquetteDTO);
    typeetiquette = typeetiquetteRepository.save(typeetiquette);
    TypeEtiquetteDTO resultDTO = TypeEtiquetteFactory.typeetiquetteToTypeEtiquetteDTO(typeetiquette);
    return resultDTO;
  }

  public TypeEtiquetteDTO update(TypeEtiquetteDTO typeetiquetteDTO) {
    log.debug("Request to update TypeEtiquette: {}",typeetiquetteDTO);
    TypeEtiquette inBase= typeetiquetteRepository.findByCode(typeetiquetteDTO.getCode());
    Preconditions.checkArgument(inBase != null, "TypeEtiquette does not exist");
    TypeEtiquetteDTO result= save(typeetiquetteDTO);
    return result;
  }

  @Transactional(
      readOnly = true
  )
  public TypeEtiquetteDTO findOne(Integer id) {
    log.debug("Request to get TypeEtiquette: {}",id);
    TypeEtiquette typeetiquette= typeetiquetteRepository.findByCode(id);
    Preconditions.checkArgument(typeetiquette != null, "TypeEtiquette does not exist");
    TypeEtiquetteDTO dto = TypeEtiquetteFactory.typeetiquetteToTypeEtiquetteDTO(typeetiquette);
    return dto;
  }

  @Transactional(
      readOnly = true
  )
  public TypeEtiquette findTypeEtiquette(Integer id) {
    log.debug("Request to get TypeEtiquette: {}",id);
    TypeEtiquette typeetiquette= typeetiquetteRepository.findByCode(id);
    Preconditions.checkArgument(typeetiquette != null, "TypeEtiquette does not exist");
    return typeetiquette;
  }

  @Transactional(
      readOnly = true
  )
  public List<TypeEtiquette> findAll() {
    log.debug("Request to get All TypeEtiquettes");
    List<TypeEtiquette> result= typeetiquetteRepository.findAll();
    return result;
  }

  public void delete(Integer id) {
    log.debug("Request to delete TypeEtiquette: {}",id);
    typeetiquetteRepository.deleteById(id);
  }
}

