package com.csys.workflowDemande.service;

import com.csys.workflowDemande.domain.Demande;
import com.csys.workflowDemande.domain.QDemande;
import com.csys.workflowDemande.dto.DemandeDTO;
import com.csys.workflowDemande.factory.DemandeFactory;
import com.csys.workflowDemande.repository.DemandeRepository;
import com.csys.workflowDemande.util.WhereClauseBuilder;
import com.google.common.base.Preconditions;
import java.lang.String;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DemandeService {

    private final Logger log = LoggerFactory.getLogger(DemandeService.class);

    private final DemandeRepository demandeRepository;

    public DemandeService(DemandeRepository demandeRepository) {
        this.demandeRepository = demandeRepository;
    }

    public DemandeDTO save(DemandeDTO demandeDTO) {
        log.debug("Request to save Demande: {}", demandeDTO);
        Demande demande = DemandeFactory.demandeDTOToDemande(demandeDTO);
        demande = demandeRepository.save(demande);
        DemandeDTO resultDTO = DemandeFactory.demandeToDemandeDTO(demande);
        return resultDTO;
    }

    public DemandeDTO update(DemandeDTO demandeDTO) {
        log.debug("Request to update Demande: {}", demandeDTO);
        Optional<Demande> inBase = demandeRepository.findById(demandeDTO.getNumeroDemande());
        Preconditions.checkArgument(inBase != null, "Demande does not exist");
        DemandeDTO result = save(demandeDTO);
        return result;
    }

    @Transactional(
            readOnly = true
    )
    public DemandeDTO findById(String id) {
        log.debug("Request to get Demande: {}", id);
        Optional<Demande> demande = demandeRepository.findById(id);
        Preconditions.checkArgument(demande != null, "Demande does not exist");
        DemandeDTO dto = DemandeFactory.demandeToDemandeDTO(demande.get());
        return dto;
    }

    @Transactional(
            readOnly = true
    )
    public Optional<Demande> findDemande(String id) {
        log.debug("Request to get Demande: {}", id);
        Optional<Demande> demande = demandeRepository.findById(id);
        Preconditions.checkArgument(demande != null, "Demande does not exist");
        return demande;
    }

    @Transactional(
            readOnly = true
    )
    public Collection<DemandeDTO> findAll() {
        log.debug("Request to get All Demandes");
        Collection<Demande> result = demandeRepository.findAll();
        return DemandeFactory.demandeToDemandeDTOs(result);
    }

    public void delete(String id) {
        log.debug("Request to delete Demande: {}", id);
        demandeRepository.deleteById(id);
    }

    public String validation(String[] nums, String user) {
        List<Demande> demandes = findAllDemandeByNums(nums);
        for (Demande demande : demandes) {
            
            int x=Integer.parseInt(demande.getEtat().getId())+1;
            demande.setEtats(String.valueOf(x));
            demandeRepository.save(demande);
        }

        return "true";
    }
//
    @Transactional(
            readOnly = true
    )
    public List<Demande> findAllDemandeByNums(String[] nums) {
        QDemande qDemande = QDemande.demande;
        WhereClauseBuilder builder = new WhereClauseBuilder()
                .optionalAnd(nums, () -> qDemande.numeroDemande.in(nums));
        return  (List<Demande>) demandeRepository.findAll(builder);
   
    }
}
