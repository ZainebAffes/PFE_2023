package com.csys.workflowDemande.dto;

import java.lang.Integer;
import java.lang.String;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TypeEtiquetteDTO {
  @NotNull
  private Integer code;

  @Size(
      min = 0,
      max = 50
  )
  private String type;

  @Size(
      min = 0,
      max = 10
  )
  private String logo;

  private List etiquetteparametragedemandeList;

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getLogo() {
    return logo;
  }

  public void setLogo(String logo) {
    this.logo = logo;
  }

  public List getEtiquetteparametragedemandeList() {
    return etiquetteparametragedemandeList;
  }

  public void setEtiquetteparametragedemandeList(List etiquetteparametragedemandeList) {
    this.etiquetteparametragedemandeList = etiquetteparametragedemandeList;
  }
}

