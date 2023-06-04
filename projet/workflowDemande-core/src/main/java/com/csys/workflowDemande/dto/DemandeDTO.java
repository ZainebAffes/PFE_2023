package com.csys.workflowDemande.dto;

import com.csys.workflowDemande.domain.Employe;
import com.csys.workflowDemande.domain.Champs;
import com.csys.workflowDemande.domain.ParametrageDemande;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.lang.String;
import java.time.LocalDateTime;
import java.util.List;
import javax.validation.constraints.Size;

public class DemandeDTO {

    private Integer code;

    private String designation;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime dateCreation;

    @Size(
            min = 0,
            max = 50
    )
    private String typeDemande;
    private String nomEmploye;
    private Employe idEmploye;
    private ParametrageDemande codeParametrageDemande;
    private Integer codeParametrage;
    private String desParametrageDemande;
    private String logoEtat;
    private String idEtat;
    private List<ChampsDTO> champsDTOs;
    private String idEmployes;
    
    public Integer getCodeParametrage() {
        return codeParametrage;
    }

    public void setCodeParametrage(Integer codeParametrage) {
        this.codeParametrage = codeParametrage;
    }

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

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getTypeDemande() {
        return typeDemande;
    }

    public void setTypeDemande(String typeDemande) {
        this.typeDemande = typeDemande;
    }

    public Employe getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(Employe idEmploye) {
        this.idEmploye = idEmploye;
    }

    public String getNomEmploye() {
        return nomEmploye;
    }

    public void setNomEmploye(String nomEmploye) {
        this.nomEmploye = nomEmploye;
    }

    public String getDesParametrageDemande() {
        return desParametrageDemande;
    }

    public void setDesParametrageDemande(String desParametrageDemande) {
        this.desParametrageDemande = desParametrageDemande;
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

    public ParametrageDemande getCodeParametrageDemande() {
        return codeParametrageDemande;
    }

    public void setCodeParametrageDemande(ParametrageDemande codeParametrageDemande) {
        this.codeParametrageDemande = codeParametrageDemande;
    }



    public String getIdEmployes() {
        return idEmployes;
    }

    public void setIdEmployes(String idEmployes) {
        this.idEmployes = idEmployes;
    }

    public List<ChampsDTO> getChampsDTOs() {
        return champsDTOs;
    }

    public void setChampsDTOs(List<ChampsDTO> champsDTOs) {
        this.champsDTOs = champsDTOs;
    }

  

}
