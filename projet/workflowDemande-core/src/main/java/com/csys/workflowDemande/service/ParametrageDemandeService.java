package com.csys.workflowDemande.service;

import com.csys.workflowDemande.domain.ParametrageDemande;
import com.csys.workflowDemande.domain.QParametrageDemande;
import com.csys.workflowDemande.dto.ParametrageDemandeDTO;
import com.csys.workflowDemande.factory.ParametrageDemandeFactory;
import com.csys.workflowDemande.repository.ParametrageDemandeRepository;
import com.csys.workflowDemande.util.WhereClauseBuilder;
import com.google.common.base.Preconditions;
import java.lang.Integer;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ParametrageDemandeService {

    private final Logger log = LoggerFactory.getLogger(ParametrageDemandeService.class);

    private final ParametrageDemandeRepository parametragedemandeRepository;

    public ParametrageDemandeService(ParametrageDemandeRepository parametragedemandeRepository) {
        this.parametragedemandeRepository = parametragedemandeRepository;
    }

    public String save(ParametrageDemandeDTO parametragedemandeDTO) {
        log.debug("Request to save ParametrageDemande: {}", parametragedemandeDTO);
        ParametrageDemande parametragedemande = ParametrageDemandeFactory.parametragedemandeDTOToParametrageDemande(parametragedemandeDTO);
     //   parametragedemande.setLogo("fas fa-cogs");
        parametragedemandeRepository.save(parametragedemande);
        return "true";
    }

    public String update(ParametrageDemandeDTO parametragedemandeDTO) {
        log.debug("Request to update ParametrageDemande: {}", parametragedemandeDTO);
        ParametrageDemande inBase = parametragedemandeRepository.findByCode(parametragedemandeDTO.getCode());
        Preconditions.checkArgument(inBase != null, "ParametrageDemande does not exist");
        ParametrageDemandeFactory.parametragedemandeToParametrageDemandeDTO(inBase);
        parametragedemandeRepository.save(inBase);
        return "true";
    }

    @Transactional(
            readOnly = true
    )
    public ParametrageDemandeDTO findOne(Integer id) {
        log.debug("Request to get ParametrageDemande: {}", id);
        ParametrageDemande parametragedemande = parametragedemandeRepository.findByCode(id);
        Preconditions.checkArgument(parametragedemande != null, "ParametrageDemande does not exist");
        ParametrageDemandeDTO dto = ParametrageDemandeFactory.parametragedemandeToParametrageDemandeDTO(parametragedemande);
        return dto;
    }

    @Transactional(
            readOnly = true
    )
    public ParametrageDemande findParametrageDemande(Integer id) {
        log.debug("Request to get ParametrageDemande: {}", id);
        ParametrageDemande parametragedemande = parametragedemandeRepository.findByCode(id);
        Preconditions.checkArgument(parametragedemande != null, "ParametrageDemande does not exist");
        return parametragedemande;
    }

    @Transactional(
            readOnly = true
    )
    public List<ParametrageDemande> findAllByDesignation(String designation) {
        QParametrageDemande qParametrageDemande = QParametrageDemande.parametrageDemande;
        WhereClauseBuilder builder = new WhereClauseBuilder()
                .optionalAnd(designation, () -> qParametrageDemande.designation.like("%" + designation + "%"));
        return (List<ParametrageDemande>) parametragedemandeRepository.findAll(builder);
    }

    @Transactional(
            readOnly = true
    )
    public List<ParametrageDemandeDTO> findAll(String designation) {
        log.debug("Request to get All ParametrageDemandes");
        List<ParametrageDemande> result;
        result = findAllByDesignation(designation);
        return ParametrageDemandeFactory.parametragedemandeToParametrageDemandeDTOs(result);
    }

    public void delete(Integer id) {
        log.debug("Request to delete ParametrageDemande: {}", id);
        parametragedemandeRepository.deleteById(id);
    }
}
