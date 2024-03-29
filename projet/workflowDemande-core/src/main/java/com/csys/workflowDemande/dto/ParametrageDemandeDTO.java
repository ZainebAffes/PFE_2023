package com.csys.workflowDemande.dto;

import com.csys.workflowDemande.domain.TypeDemande;
import java.lang.Integer;
import java.lang.String;
import java.util.List;
import javax.validation.constraints.Size;

public class ParametrageDemandeDTO {

    private Integer code;

    @Size(
            min = 0,
            max = 50
    )
    private String designation;
    private String descriptionTypeDemande;
    private List<EtiquetteparametragedemandeDTO> etiquetteparametragedemandeDTOs;
    private TypeDemande typeDemandes;
    @Size(
            min = 0,
            max = 255
    )
    private String logo;
    private String codeTypeDemande;
    private String logoEtat;
    private String idEtat;
    private String etats;

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

    public TypeDemande getTypeDemandes() {
        return typeDemandes;
    }

    public void setTypeDemandes(TypeDemande typeDemandes) {
        this.typeDemandes = typeDemandes;
    }

    public String getCodeTypeDemande() {
        return codeTypeDemande;
    }

    public void setCodeTypeDemande(String codeTypeDemande) {
        this.codeTypeDemande = codeTypeDemande;
    }

    public String getDescriptionTypeDemande() {
        return descriptionTypeDemande;
    }

    public void setDescriptionTypeDemande(String descriptionTypeDemande) {
        this.descriptionTypeDemande = descriptionTypeDemande;
    }

    public List<EtiquetteparametragedemandeDTO> getEtiquetteparametragedemandeDTOs() {
        return etiquetteparametragedemandeDTOs;
    }

    public void setEtiquetteparametragedemandeDTOs(List<EtiquetteparametragedemandeDTO> etiquetteparametragedemandeDTOs) {
        this.etiquetteparametragedemandeDTOs = etiquetteparametragedemandeDTOs;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getLogoEtat() {
        return logoEtat;
    }

    public void setLogoEtat(String logoEtat) {
        this.logoEtat = logoEtat;
    }

    public String getIdEtat() {
        return idEtat;
    }

    public void setIdEtat(String idEtat) {
        this.idEtat = idEtat;
    }

    public String getEtats() {
        return etats;
    }

    public void setEtats(String etats) {
        this.etats = etats;
    }

}
