package com.csys.workflowDemande.service;

import com.csys.workflowDemande.domain.Champs;
import com.csys.workflowDemande.domain.QChamps;
import com.csys.workflowDemande.dto.ChampsDTO;
import com.csys.workflowDemande.factory.ChampsFactory;
import com.csys.workflowDemande.repository.ChampsRepository;
import com.csys.workflowDemande.util.WhereClauseBuilder;
import com.google.common.base.Preconditions;
import java.lang.Integer;
import java.util.Collection;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ChampsService {
  private final Logger log = LoggerFactory.getLogger(ChampsService.class);

  private final ChampsRepository champsRepository;

  public ChampsService(ChampsRepository champsRepository) {
    this.champsRepository=champsRepository;
  }

  public ChampsDTO save(ChampsDTO champsDTO) {
    log.debug("Request to save Champs: {}",champsDTO);
    Champs champs = ChampsFactory.champsDTOToChamps(champsDTO);
    champs = champsRepository.save(champs);
    ChampsDTO resultDTO = ChampsFactory.champsToChampsDTO(champs);
    return resultDTO;
  }

  public ChampsDTO update(ChampsDTO champsDTO) {
    log.debug("Request to update Champs: {}",champsDTO);
    Champs inBase= champsRepository.findById(champsDTO.getCode()).get();
    Preconditions.checkArgument(inBase != null, "Champs does not exist");
    ChampsDTO result= save(champsDTO);
    return result;
  }

  @Transactional(
      readOnly = true
  )
  public ChampsDTO findOne(Integer id) {
    log.debug("Request to get Champs: {}",id);
    Champs champs= champsRepository.findById(id).get();
    Preconditions.checkArgument(champs != null, "Champs does not exist");
    ChampsDTO dto = ChampsFactory.champsToChampsDTO(champs);
    return dto;
  }

  @Transactional(
      readOnly = true
  )
  public Champs findChamps(Integer id) {
    log.debug("Request to get Champs: {}",id);
    Champs champs= champsRepository.findById(id).get();
    Preconditions.checkArgument(champs != null, "Champs does not exist");
    return champs;
  }

  @Transactional(
      readOnly = true
  )
  public Collection<ChampsDTO> findAll() {
    log.debug("Request to get All Champss");
    List<Champs> result= champsRepository.findAll();
    return ChampsFactory.listdeschampsToChampsDTOs(result);
  }

  public void delete(Integer id) {
    log.debug("Request to delete Champs: {}",id);
    champsRepository.deleteById(id);
  }
  
   public List<Champs> findAllChampsByCodeDemande(Integer codeDemande) {
        QChamps qChamps = QChamps.champs;
       
        WhereClauseBuilder builder = new WhereClauseBuilder()
             
                .optionalAnd(codeDemande, () -> qChamps.demande().code.eq(codeDemande));
        return (List<Champs>) champsRepository.findAll(builder);
}
   }


