package com.csys.workflowDemande.service;

import com.csys.workflowDemande.domain.Demande;
import com.csys.workflowDemande.domain.QDemande;
import com.csys.workflowDemande.dto.DemandeDTO;
import com.csys.workflowDemande.factory.DemandeFactory;
import com.csys.workflowDemande.repository.DemandeRepository;
import com.csys.workflowDemande.util.WhereClauseBuilder;
import com.csys.workflowDemande.util.Preconditions;
import java.lang.String;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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

    @Transactional(
            readOnly = true
    )
    public DemandeDTO findById(Integer id) {
        log.debug("Request to get Demande: {}", id);
        Optional<Demande> demande = demandeRepository.findById(id);
        Preconditions.checkBusinessLogique(demande != null, "Demande does not exist");
        DemandeDTO dto = DemandeFactory.demandeToDemandeDTO(demande.get());
        return dto;
    }

    @Transactional(
            readOnly = true
    )
    public Optional<Demande> findDemande(Integer id) {
        log.debug("Request to get Demande: {}", id);
        Optional<Demande> demande = demandeRepository.findById(id);
        Preconditions.checkBusinessLogique(demande != null, "Demande does not exist");
        return demande;
    }

    @Transactional(
            readOnly = true
    )
    public Collection<DemandeDTO> findAll(Integer codeParametrage) {
        log.debug("Request to get All Demandes");
        Collection<Demande> result = findAllDemandeByNums(null, codeParametrage);
        return DemandeFactory.demandeToDemandeDTOs(result);
    }

    public void delete(Integer id) {
        log.debug("Request to delete Demande: {}", id);
        demandeRepository.deleteById(id);
    }

    public String validation(String[] nums, String user, boolean refus ) {
        List<Demande> demandes = findAllDemandeByNums(nums, null);
     if(!refus){
        for (Demande demande : demandes) {

            int x = Integer.parseInt(demande.getEtat().getCode()) + 1;
            demande.setEtats(String.valueOf(x));
            demandeRepository.save(demande);
        }}
     else{
          for (Demande demande : demandes) {
            demande.setEtats(String.valueOf(-1));
            demandeRepository.save(demande);
        }
     }

        return "true";
    }
//

    @Transactional(
            readOnly = true
    )
    public List<Demande> findAllDemandeByNums(String[] nums, Integer codeParametrage) {
        QDemande qDemande = QDemande.demande;
        List<Integer> intList = new ArrayList<Integer>();
        if (nums !=null ) {
            for (String s : nums) {
                intList.add(Integer.valueOf(s));
            }
        }
        WhereClauseBuilder builder = new WhereClauseBuilder()
                .optionalAnd(nums, () -> qDemande.code.in(intList))
                .optionalAnd(codeParametrage, () -> qDemande.codeParametrageDemande().code.eq(codeParametrage));
        return (List<Demande>) demandeRepository.findAll(builder);

    }

    public String save(DemandeDTO demandeDTO) {
        log.debug("Request to save Demande: {}", demandeDTO);
        Demande demande = DemandeFactory.demandeDTOToDemande(demandeDTO, null);
        demande.setEtats("0");
        demandeRepository.save(demande);
        return "true";
    }

    public String update(DemandeDTO demandeDTO) {
        log.debug("Request to update Demande: {}", demandeDTO);
        Demande inBase = demandeRepository.findByCode(demandeDTO.getCode());
        Preconditions.checkBusinessLogique(inBase != null, "Demande does not exist");
        DemandeFactory.demandeDTOToDemande(demandeDTO, inBase);
        demandeRepository.save(inBase);
        return "true";
    }

}
