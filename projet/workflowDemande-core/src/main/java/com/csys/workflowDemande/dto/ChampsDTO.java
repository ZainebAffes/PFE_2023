package com.csys.workflowDemande.dto;

import java.lang.Integer;
import java.lang.String;
import javax.validation.constraints.Size;

public class ChampsDTO {

    private Integer code;

    @Size(
            min = 0,
            max = 50
    )
    private String nomChamp;

    private String valeur;

    private String Codedemande;
    private Integer codeTypeEtiquette;
    private TypeEtiquetteDTO typeEtiquetteDTO;

    public ChampsDTO() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getNomChamp() {
        return nomChamp;
    }

    public void setNomChamp(String nomChamp) {
        this.nomChamp = nomChamp;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public String getCodedemande() {
        return Codedemande;
    }

    public void setCodedemande(String Codedemande) {
        this.Codedemande = Codedemande;
    }

   

    public Integer getCodeTypeEtiquette() {
        return codeTypeEtiquette;
    }

    public void setCodeTypeEtiquette(Integer codeTypeEtiquette) {
        this.codeTypeEtiquette = codeTypeEtiquette;
    }

    public TypeEtiquetteDTO getTypeEtiquetteDTO() {
        return typeEtiquetteDTO;
    }

    public void setTypeEtiquetteDTO(TypeEtiquetteDTO typeEtiquetteDTO) {
        this.typeEtiquetteDTO = typeEtiquetteDTO;
    }

}
