package com.csys.workflowDemande.factory;

import com.csys.workflowDemande.domain.Etiquetteparametragedemande;
import com.csys.workflowDemande.domain.OptionEtiquette;
import com.csys.workflowDemande.dto.EtiquetteparametragedemandeDTO;
import java.util.ArrayList;

import java.util.List;

public class EtiquetteparametragedemandeFactory {
    
    public static EtiquetteparametragedemandeDTO etiquetteparametragedemandeToEtiquetteparametragedemandeDTO(Etiquetteparametragedemande etiquetteparametragedemande) {
        EtiquetteparametragedemandeDTO etiquetteparametragedemandeDTO = new EtiquetteparametragedemandeDTO();
        etiquetteparametragedemandeDTO.setCode(etiquetteparametragedemande.getCode());
        etiquetteparametragedemandeDTO.setDescription(etiquetteparametragedemande.getDescription());
        etiquetteparametragedemandeDTO.setMin(etiquetteparametragedemande.getMin());
        etiquetteparametragedemandeDTO.setMax(etiquetteparametragedemande.getMax());
        etiquetteparametragedemandeDTO.setIsRequired(etiquetteparametragedemande.getIsRequired());
        etiquetteparametragedemandeDTO.setPosition(etiquetteparametragedemande.getPosition());
        etiquetteparametragedemandeDTO.setDefultValue(etiquetteparametragedemande.getDefultValue());
        etiquetteparametragedemandeDTO.setVisible(etiquetteparametragedemande.getVisible());
        etiquetteparametragedemandeDTO.setMultiple(etiquetteparametragedemande.getMultiple());
        etiquetteparametragedemandeDTO.setOptionEtiquetteDTOs(OptionEtiquetteFactory.optionetiquetteToOptionEtiquetteDTOs(etiquetteparametragedemande.getOptionEtiquettes()));
        etiquetteparametragedemandeDTO.setTypeEtiquetteDTO(TypeEtiquetteFactory.typeetiquetteToTypeEtiquetteDTO(etiquetteparametragedemande.getTypeEtiquette()));
        return etiquetteparametragedemandeDTO;
    }
    
    public static Etiquetteparametragedemande etiquetteparametragedemandeDTOToEtiquetteparametragedemande(EtiquetteparametragedemandeDTO etiquetteparametragedemandeDTO) {
        Etiquetteparametragedemande etiquetteparametragedemande = new Etiquetteparametragedemande();
        etiquetteparametragedemande.setCode(etiquetteparametragedemandeDTO.getCode());
        etiquetteparametragedemande.setDescription(etiquetteparametragedemandeDTO.getDescription());
        etiquetteparametragedemande.setMin(etiquetteparametragedemandeDTO.getMin());
        etiquetteparametragedemande.setMax(etiquetteparametragedemandeDTO.getMax());
        etiquetteparametragedemande.setIsRequired(etiquetteparametragedemandeDTO.getIsRequired());
        etiquetteparametragedemande.setPosition(etiquetteparametragedemandeDTO.getPosition());
        etiquetteparametragedemande.setDefultValue(etiquetteparametragedemandeDTO.getDefultValue());
        etiquetteparametragedemande.setVisible(etiquetteparametragedemandeDTO.getVisible());
        etiquetteparametragedemande.setMultiple(etiquetteparametragedemandeDTO.getMultiple());
        
         List<OptionEtiquette> optionEtiquetteLists = new ArrayList<>();
        etiquetteparametragedemandeDTO.getOptionEtiquetteDTOs().forEach(x -> {
            x.setOptionEtiquette(etiquetteparametragedemandeDTO.getCode());
            OptionEtiquette optionEtiquette = OptionEtiquetteFactory.optionetiquetteDTOToOptionEtiquette(x);
            optionEtiquetteLists.add(optionEtiquette);
        });
        if (etiquetteparametragedemande.getOptionEtiquettes() != null) {
            etiquetteparametragedemande.getOptionEtiquettes().clear();
            etiquetteparametragedemande.getOptionEtiquettes().addAll(optionEtiquetteLists);
        } else {
            etiquetteparametragedemande.setOptionEtiquettes(optionEtiquetteLists);
        }
        etiquetteparametragedemande.setCodeTypeEtiquette(etiquetteparametragedemandeDTO.getCodeTypeEtiquette());
        return etiquetteparametragedemande;
    }
    
    public static List<EtiquetteparametragedemandeDTO> etiquetteparametragedemandeToEtiquetteparametragedemandeDTOs(List<Etiquetteparametragedemande> etiquetteparametragedemandes) {
        List<EtiquetteparametragedemandeDTO> etiquetteparametragedemandesDTO = new ArrayList<>();
        etiquetteparametragedemandes.forEach(x -> {
            etiquetteparametragedemandesDTO.add(etiquetteparametragedemandeToEtiquetteparametragedemandeDTO(x));
        });
        return etiquetteparametragedemandesDTO;
    }
}
