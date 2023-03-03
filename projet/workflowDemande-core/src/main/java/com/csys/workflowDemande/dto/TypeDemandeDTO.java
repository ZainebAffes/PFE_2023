package com.csys.workflowDemande.dto;

import java.lang.String;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TypeDemandeDTO {
  @NotNull
  @Size(
      min = 1,
      max = 50
  )
  private String codeTypeDemande;

  @Size(
      min = 0,
      max = 50
  )
  private String description;

  @Size(
      min = 0,
      max = 50
  )
  private String nom;

  public String getCodeTypeDemande() {
    return codeTypeDemande;
  }

  public void setCodeTypeDemande(String codeTypeDemande) {
    this.codeTypeDemande = codeTypeDemande;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }
}

