package com.csys.workflowDemande.service;

import com.csys.workflowDemande.domain.Etat;
import com.csys.workflowDemande.dto.EtatDTO;
import com.csys.workflowDemande.factory.EtatFactory;
import com.csys.workflowDemande.repository.EtatRepository;
import com.csys.workflowDemande.util.Preconditions;
import java.lang.String;
import java.util.Collection;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EtatService {
  private final Logger log = LoggerFactory.getLogger(EtatService.class);

  private final EtatRepository etatRepository;

  public EtatService(EtatRepository etatRepository) {
    this.etatRepository=etatRepository;
  }

  public EtatDTO save(EtatDTO etatDTO) {
    log.debug("Request to save Etat: {}",etatDTO);
    Etat etat = EtatFactory.etatDTOToEtat(etatDTO);
    etat = etatRepository.save(etat);
    EtatDTO resultDTO = EtatFactory.etatToEtatDTO(etat);
    return resultDTO;
  }

  public EtatDTO update(EtatDTO etatDTO) {
    log.debug("Request to update Etat: {}",etatDTO);
      Optional<Etat> inBase= etatRepository.findById(etatDTO.getCode());
    Preconditions.checkBusinessLogique(inBase != null, "Etat does not exist");
    EtatDTO result= save(etatDTO);
    return result;
  }

  @Transactional(
      readOnly = true
  )
  public EtatDTO findOne(String id) {
    log.debug("Request to get Etat: {}",id);
      Optional<Etat> etat= etatRepository.findById(id);
    Preconditions.checkBusinessLogique(etat != null, "Etat does not exist");
    EtatDTO dto = EtatFactory.etatToEtatDTO(etat.get());
    return dto;
  }

  @Transactional(
      readOnly = true
  )
  public Etat findEtat(String id) {
    log.debug("Request to get Etat: {}",id);
      Optional<Etat> etat= etatRepository.findById(id);
   Preconditions.checkBusinessLogique(etat != null, "Etat does not exist");
    return etat.get();
  }

  @Transactional(
      readOnly = true
  )
  public Collection<EtatDTO> findAll() {
    log.debug("Request to get All Etats");
    Collection<Etat> result= etatRepository.findAll();
    return EtatFactory.etatToEtatDTOs(result);
  }

  public void delete(String id) {
    log.debug("Request to delete Etat: {}",id);
    etatRepository.deleteById(id);
  }
}

