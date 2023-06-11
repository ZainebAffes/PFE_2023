package com.csys.workflowDemande.service;

import com.csys.workflowDemande.domain.ParametrageDemande;
import com.csys.workflowDemande.domain.QTypeDemande;
import com.csys.workflowDemande.domain.TypeDemande;
import com.csys.workflowDemande.dto.TypeDemandeDTO;
import com.csys.workflowDemande.factory.TypeDemandeFactory;
import com.csys.workflowDemande.repository.TypeDemandeRepository;
import java.lang.String;

import java.util.Collection;

import java.util.List;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.csys.workflowDemande.util.Preconditions;
import com.csys.workflowDemande.util.WhereClauseBuilder;

@Service
@Transactional
public class TypeDemandeService {

    private final Logger log = LoggerFactory.getLogger(TypeDemandeService.class);

    private final TypeDemandeRepository typedemandeRepository;
    private final ParametrageDemandeService parametrageDemandeService;

    public TypeDemandeService(TypeDemandeRepository typedemandeRepository, ParametrageDemandeService parametrageDemandeService) {
        this.typedemandeRepository = typedemandeRepository;
        this.parametrageDemandeService = parametrageDemandeService;
    }

    public TypeDemandeDTO save(TypeDemandeDTO typedemandeDTO) {
        log.debug("Request to save TypeDemande: {}", typedemandeDTO);
        TypeDemande inBase = typedemandeRepository.findByCodeTypeDemande(typedemandeDTO.getCodeTypeDemande());
        Preconditions.checkBusinessLogique(inBase == null, "TypeDemande exist");
        TypeDemande typedemande = TypeDemandeFactory.typedemandeDTOToTypeDemande(typedemandeDTO);
        typedemande = typedemandeRepository.save(typedemande);
        TypeDemandeDTO resultDTO = TypeDemandeFactory.typedemandeToTypeDemandeDTO(typedemande);
        return resultDTO;
    }

    public TypeDemandeDTO update(TypeDemandeDTO typedemandeDTO) {
        log.debug("Request to update TypeDemande: {}", typedemandeDTO);
        TypeDemande inBase = typedemandeRepository.findById(typedemandeDTO.getCodeTypeDemande()).get();
        Preconditions.checkBusinessLogique(inBase != null, "TypeDemande does not exist");
        TypeDemandeDTO result = save(typedemandeDTO);
        return result;
    }

    @Transactional(
            readOnly = true
    )
    public TypeDemandeDTO findOne(String id) {
        log.debug("Request to get TypeDemande: {}", id);
        Optional<TypeDemande> typedemande = typedemandeRepository.findById(id);
        Preconditions.checkBusinessLogique(typedemande != null, "TypeDemande does not exist");
        TypeDemandeDTO dto = TypeDemandeFactory.typedemandeToTypeDemandeDTO(typedemande.get());
        return dto;
    }

    @Transactional(
            readOnly = true
    )
    public TypeDemande findTypeDemande(String id) {
        log.debug("Request to get TypeDemande: {}", id);
        Optional<TypeDemande> typedemande = typedemandeRepository.findById(id);
        Preconditions.checkBusinessLogique(typedemande != null, "TypeDemande does not exist");
        return typedemande.get();
    }

    @Transactional(
            readOnly = true
    )
    public List<TypeDemandeDTO> findAll(String designation) {
        log.debug("Request to get AllfindAll TypeDemandes");
        List<TypeDemande> result;
        result = findAllTypeDemandes(designation);
        return TypeDemandeFactory.typedemandeToTypeDemandeDTOs(result);
    }

    private List<TypeDemande> findAllTypeDemandes(String designation) {
        QTypeDemande qTypeDemande = QTypeDemande.typeDemande;
        WhereClauseBuilder builder = new WhereClauseBuilder()
                .optionalAnd(designation, () -> qTypeDemande.description.like("%" + designation + "%"));
        List<TypeDemande> result = (List<TypeDemande>) typedemandeRepository.findAll(builder);
        return result;
    }

    public void delete(String id) {
        log.debug("Request to delete type-demande devis: {}", id);
        TypeDemande inBase = findTypeDemande(id);

        Preconditions.checkBusinessLogique(inBase != null, "type-demande.NotFound");
        List<ParametrageDemande> parametrageDemandes = parametrageDemandeService.findAllByDesignation(null, id);
        Preconditions.checkBusinessLogique(parametrageDemandes == null, "TypeDemande utilisé dans un paramétrage du désignation : " + parametrageDemandes.get(0).getDesignation());

        typedemandeRepository.deleteById(id);
    }

    public void deleteById(String id) {
        throw new UnsupportedOperationException("Not suppor  ted yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<TypeDemandeDTO> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
