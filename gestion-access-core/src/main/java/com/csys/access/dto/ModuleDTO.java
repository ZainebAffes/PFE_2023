package com.csys.access.dto;

import java.lang.Integer;
import java.lang.String;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ModuleDTO {
  @NotNull
  @Size(
      
      max = 3
  )
  private String numModule;

  @Size(
      min = 0,
      max = 100
  )
  private String desModule;

  @NotNull
  @Size(
      
      max = 3
  )
  private String codeModule;

  @NotNull
  private Integer revision;

  @NotNull
  @Size(
      
      max = 50
  )
  private String grp;

  @NotNull
  @Size(
      
      max = 50
  )
  private String chemin;

  @NotNull
  private boolean afficher;

  private byte[] image;

  @Size(
      min = 0,
      max = 50
  )
  private String nomImage;

  @NotNull
  @Size(
      
      max = 20
  )
  private String processusName;

  @NotNull
  @Size(
      
      max = 50
  )
  private String versionDatabase;

  @NotNull
  @Size(
      
      max = 50
  )
  private String descModule;

  @Size(
      min = 0,
      max = 1073741823
  )
  private String desModuleBrut;

  @NotNull
  @Size(
      
      max = 200
  )
  private String centres;

  @NotNull
  @Size(
      
      max = 10
  )
  private String versionModule;

  @Size(
      min = 0,
      max = 100
  )
  private String designationSec;

  public String getNumModule() {
    return numModule;
  }

  public void setNumModule(String numModule) {
    this.numModule = numModule;
  }

  public String getDesModule() {
    return desModule;
  }

  public void setDesModule(String desModule) {
    this.desModule = desModule;
  }

  public String getCodeModule() {
    return codeModule;
  }

  public void setCodeModule(String codeModule) {
    this.codeModule = codeModule;
  }

  public Integer getRevision() {
    return revision;
  }

  public void setRevision(Integer revision) {
    this.revision = revision;
  }

  public String getGrp() {
    return grp;
  }

  public void setGrp(String grp) {
    this.grp = grp;
  }

  public String getChemin() {
    return chemin;
  }

  public void setChemin(String chemin) {
    this.chemin = chemin;
  }

  public boolean getAfficher() {
    return afficher;
  }

  public void setAfficher(boolean afficher) {
    this.afficher = afficher;
  }

  public byte[] getImage() {
    return image;
  }

  public void setImage(byte[] image) {
    this.image = image;
  }

  public String getNomImage() {
    return nomImage;
  }

  public void setNomImage(String nomImage) {
    this.nomImage = nomImage;
  }

  public String getProcessusName() {
    return processusName;
  }

  public void setProcessusName(String processusName) {
    this.processusName = processusName;
  }

  public String getVersionDatabase() {
    return versionDatabase;
  }

  public void setVersionDatabase(String versionDatabase) {
    this.versionDatabase = versionDatabase;
  }

  public String getDescModule() {
    return descModule;
  }

  public void setDescModule(String descModule) {
    this.descModule = descModule;
  }

  public String getDesModuleBrut() {
    return desModuleBrut;
  }

  public void setDesModuleBrut(String desModuleBrut) {
    this.desModuleBrut = desModuleBrut;
  }

  public String getCentres() {
    return centres;
  }

  public void setCentres(String centres) {
    this.centres = centres;
  }

  public String getVersionModule() {
    return versionModule;
  }

  public void setVersionModule(String versionModule) {
    this.versionModule = versionModule;
  }

  public String getDesignationSec() {
    return designationSec;
  }

  public void setDesignationSec(String designationSec) {
    this.designationSec = designationSec;
  }
}

