package com.csys.workflowDemande.service;

import com.csys.workflowDemande.domain.OptionEtiquette;
import com.csys.workflowDemande.dto.OptionEtiquetteDTO;
import com.csys.workflowDemande.factory.OptionEtiquetteFactory;
import com.csys.workflowDemande.repository.OptionEtiquetteRepository;
import com.google.common.base.Preconditions;
import java.lang.Integer;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OptionEtiquetteService {
  private final Logger log = LoggerFactory.getLogger(OptionEtiquetteService.class);

  private final OptionEtiquetteRepository optionetiquetteRepository;

  public OptionEtiquetteService(OptionEtiquetteRepository optionetiquetteRepository) {
    this.optionetiquetteRepository=optionetiquetteRepository;
  }

  public OptionEtiquetteDTO save(OptionEtiquetteDTO optionetiquetteDTO) {
    log.debug("Request to save OptionEtiquette: {}",optionetiquetteDTO);
    OptionEtiquette optionetiquette = OptionEtiquetteFactory.optionetiquetteDTOToOptionEtiquette(optionetiquetteDTO);
    optionetiquette = optionetiquetteRepository.save(optionetiquette);
    OptionEtiquetteDTO resultDTO = OptionEtiquetteFactory.optionetiquetteToOptionEtiquetteDTO(optionetiquette);
    return resultDTO;
  }

  public OptionEtiquetteDTO update(OptionEtiquetteDTO optionetiquetteDTO) {
    log.debug("Request to update OptionEtiquette: {}",optionetiquetteDTO);
    OptionEtiquette inBase= optionetiquetteRepository.findByCodeOption(optionetiquetteDTO.getCodeOption());
    Preconditions.checkArgument(inBase != null, "OptionEtiquette does not exist");
    OptionEtiquetteDTO result= save(optionetiquetteDTO);
    return result;
  }

  @Transactional(
      readOnly = true
  )
  public OptionEtiquetteDTO findOne(Integer id) {
    log.debug("Request to get OptionEtiquette: {}",id);
    OptionEtiquette optionetiquette= optionetiquetteRepository.findByCodeOption(id);
    Preconditions.checkArgument(optionetiquette != null, "OptionEtiquette does not exist");
    OptionEtiquetteDTO dto = OptionEtiquetteFactory.optionetiquetteToOptionEtiquetteDTO(optionetiquette);
    return dto;
  }

  @Transactional(
      readOnly = true
  )
  public OptionEtiquette findOptionEtiquette(Integer id) {
    log.debug("Request to get OptionEtiquette: {}",id);
    OptionEtiquette optionetiquette= optionetiquetteRepository.findByCodeOption(id);
    Preconditions.checkArgument(optionetiquette != null, "OptionEtiquette does not exist");
    return optionetiquette;
  }

  @Transactional(
      readOnly = true
  )
  public List<OptionEtiquetteDTO> findAll() {
    log.debug("Request to get All OptionEtiquettes");
    List<OptionEtiquette> result= optionetiquetteRepository.findAll();
    return OptionEtiquetteFactory.optionetiquetteToOptionEtiquetteDTOs(result);
  }

  public void delete(Integer id) {
    log.debug("Request to delete OptionEtiquette: {}",id);
    optionetiquetteRepository.deleteById(id);
  }
}

