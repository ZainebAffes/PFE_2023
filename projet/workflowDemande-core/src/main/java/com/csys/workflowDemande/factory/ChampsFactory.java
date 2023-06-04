package com.csys.workflowDemande.factory;

import com.csys.workflowDemande.domain.Champs;
import com.csys.workflowDemande.dto.ChampsDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.List;

public class ChampsFactory {

    public static ChampsDTO champsToChampsDTO(Champs champs) {
        ChampsDTO champsDTO = new ChampsDTO();
        champsDTO.setCode(champs.getCode());
        champsDTO.setNomChamp(champs.getNomChamp());
        champsDTO.setValeur(champs.getValeur());
        champsDTO.setTypeEtiquetteDTO(TypeEtiquetteFactory.typeetiquetteToTypeEtiquetteDTO(champs.getTypeEtiquette()));

        return champsDTO;
    }

    public static Champs champsDTOToChamps(ChampsDTO champsDTO) {
        Champs champs = new Champs();
        champs.setCode(champsDTO.getCode());
        champs.setNomChamp(champsDTO.getNomChamp());
        champs.setValeur(champsDTO.getValeur());
        champs.setCodeDemande(champsDTO.getCodedemande());
        champs.setCodeTypeEtiquette(champsDTO.getCodeTypeEtiquette());

        return champs;
    }

    public static List<ChampsDTO> listdeschampsToChampsDTOs(List<Champs> champss) {
        List<ChampsDTO> champssDTO = new ArrayList<>();
        champss.forEach(x -> {
            champssDTO.add(champsToChampsDTO(x));
        });
        return champssDTO;
    }
}
