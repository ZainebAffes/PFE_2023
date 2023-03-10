package com.csys.workflowDemande.service;

import com.csys.workflowDemande.domain.Etiquetteparametragedemande;
import com.csys.workflowDemande.dto.EtiquetteparametragedemandeDTO;
import com.csys.workflowDemande.factory.EtiquetteparametragedemandeFactory;
import com.csys.workflowDemande.repository.EtiquetteparametragedemandeRepository;
import com.google.common.base.Preconditions;
import java.lang.Integer;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EtiquetteparametragedemandeService {
  private final Logger log = LoggerFactory.getLogger(EtiquetteparametragedemandeService.class);

  private final EtiquetteparametragedemandeRepository etiquetteparametragedemandeRepository;

  public EtiquetteparametragedemandeService(EtiquetteparametragedemandeRepository etiquetteparametragedemandeRepository) {
    this.etiquetteparametragedemandeRepository=etiquetteparametragedemandeRepository;
  }

  public EtiquetteparametragedemandeDTO save(EtiquetteparametragedemandeDTO etiquetteparametragedemandeDTO) {
    log.debug("Request to save Etiquetteparametragedemande: {}",etiquetteparametragedemandeDTO);
    Etiquetteparametragedemande etiquetteparametragedemande = EtiquetteparametragedemandeFactory.etiquetteparametragedemandeDTOToEtiquetteparametragedemande(etiquetteparametragedemandeDTO);
    etiquetteparametragedemande = etiquetteparametragedemandeRepository.save(etiquetteparametragedemande);
    EtiquetteparametragedemandeDTO resultDTO = EtiquetteparametragedemandeFactory.etiquetteparametragedemandeToEtiquetteparametragedemandeDTO(etiquetteparametragedemande);
    return resultDTO;
  }

  public EtiquetteparametragedemandeDTO update(EtiquetteparametragedemandeDTO etiquetteparametragedemandeDTO) {
    log.debug("Request to update Etiquetteparametragedemande: {}",etiquetteparametragedemandeDTO);
    Etiquetteparametragedemande inBase= etiquetteparametragedemandeRepository.findByCode(etiquetteparametragedemandeDTO.getCode());
    Preconditions.checkArgument(inBase != null, "Etiquetteparametragedemande does not exist");
    EtiquetteparametragedemandeDTO result= save(etiquetteparametragedemandeDTO);
    return result;
  }

  @Transactional(
      readOnly = true
  )
  public EtiquetteparametragedemandeDTO findOne(Integer id) {
    log.debug("Request to get Etiquetteparametragedemande: {}",id);
    Etiquetteparametragedemande etiquetteparametragedemande= etiquetteparametragedemandeRepository.findByCode(id);
    Preconditions.checkArgument(etiquetteparametragedemande != null, "Etiquetteparametragedemande does not exist");
    EtiquetteparametragedemandeDTO dto = EtiquetteparametragedemandeFactory.etiquetteparametragedemandeToEtiquetteparametragedemandeDTO(etiquetteparametragedemande);
    return dto;
  }

  @Transactional(
      readOnly = true
  )
  public Etiquetteparametragedemande findEtiquetteparametragedemande(Integer id) {
    log.debug("Request to get Etiquetteparametragedemande: {}",id);
    Etiquetteparametragedemande etiquetteparametragedemande= etiquetteparametragedemandeRepository.findByCode(id);
    Preconditions.checkArgument(etiquetteparametragedemande != null, "Etiquetteparametragedemande does not exist");
    return etiquetteparametragedemande;
  }

  @Transactional(
      readOnly = true
  )
  public List<EtiquetteparametragedemandeDTO> findAll() {
    log.debug("Request to get All Etiquetteparametragedemandes");
    List<Etiquetteparametragedemande> result= etiquetteparametragedemandeRepository.findAll();
    return EtiquetteparametragedemandeFactory.etiquetteparametragedemandeToEtiquetteparametragedemandeDTOs(result);
  }

  public void delete(Integer id) {
    log.debug("Request to delete Etiquetteparametragedemande: {}",id);
    etiquetteparametragedemandeRepository.deleteById(id);
  }
}

