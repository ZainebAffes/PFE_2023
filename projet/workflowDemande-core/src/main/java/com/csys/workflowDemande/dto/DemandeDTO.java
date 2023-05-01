package com.csys.workflowDemande.dto;

import com.csys.workflowDemande.domain.Employe;
import com.csys.workflowDemande.domain.ParametrageDemande;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.lang.String;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class DemandeDTO {

    @NotNull
    @Size(
            min = 1,
            max = 50
    )
    private String numeroDemande;

    @Size(
            min = 0,
            max = 50
    )
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
    private String desParametrageDemande;
    private String logoEtat;
    private String idEtat;

    public String getNumeroDemande() {
        return numeroDemande;
    }

    public void setNumeroDemande(String numeroDemande) {
        this.numeroDemande = numeroDemande;
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
}
