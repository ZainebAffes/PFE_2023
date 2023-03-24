package com.csys.workflowDemande.dto;

import com.csys.workflowDemande.domain.Etiquetteparametragedemande;
import com.csys.workflowDemande.domain.TypeDemande;
import java.lang.Integer;
import java.lang.String;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ParametrageDemandeDTO {

    @NotNull
    private Integer code;

    @Size(
            min = 0,
            max = 10
    )
    private String designation;
    private String descriptionTypeDemande;
    private Etiquetteparametragedemande codeParametrageEtiquette;

    private TypeDemande codeTypeDemande;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Etiquetteparametragedemande getCodeParametrageEtiquette() {
        return codeParametrageEtiquette;
    }

    public void setCodeParametrageEtiquette(Etiquetteparametragedemande codeParametrageEtiquette) {
        this.codeParametrageEtiquette = codeParametrageEtiquette;
    }

    public TypeDemande getCodeTypeDemande() {
        return codeTypeDemande;
    }

    public void setCodeTypeDemande(TypeDemande codeTypeDemande) {
        this.codeTypeDemande = codeTypeDemande;
    }

    public String getDescriptionTypeDemande() {
        return descriptionTypeDemande;
    }

    public void setDescriptionTypeDemande(String descriptionTypeDemande) {
        this.descriptionTypeDemande = descriptionTypeDemande;
    }
    
}
