package com.csys.workflowDemande.dto;

import com.csys.workflowDemande.domain.Empolye;
import com.csys.workflowDemande.domain.ParametrageDemande;
import java.lang.String;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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

  @Temporal(TemporalType.DATE)
  private Date dateCreation;

  @Size(
      min = 0,
      max = 50
  )
  private String typeDemande;

 // private Empolye idEmploye;

  private ParametrageDemande codeParametrageDemande;

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

  public Date getDateCreation() {
    return dateCreation;
  }

  public void setDateCreation(Date dateCreation) {
    this.dateCreation = dateCreation;
  }

  public String getTypeDemande() {
    return typeDemande;
  }

  public void setTypeDemande(String typeDemande) {
    this.typeDemande = typeDemande;
  }

//  public Empolye getIdEmploye() {
//    return idEmploye;
//  }
//
//  public void setIdEmploye(Empolye idEmploye) {
//    this.idEmploye = idEmploye;
//  }

  public ParametrageDemande getCodeParametrageDemande() {
    return codeParametrageDemande;
  }

  public void setCodeParametrageDemande(ParametrageDemande codeParametrageDemande) {
    this.codeParametrageDemande = codeParametrageDemande;
  }
}

