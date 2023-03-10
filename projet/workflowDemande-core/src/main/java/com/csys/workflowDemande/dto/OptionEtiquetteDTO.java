package com.csys.workflowDemande.dto;

import java.lang.Integer;
import java.lang.String;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class OptionEtiquetteDTO {
  @NotNull
  private Integer codeOption;

  @Size(
      min = 0,
      max = 10
  )
  private String choix;

  private List etiquetteparametragedemandeList;

  public Integer getCodeOption() {
    return codeOption;
  }

  public void setCodeOption(Integer codeOption) {
    this.codeOption = codeOption;
  }

  public String getChoix() {
    return choix;
  }

  public void setChoix(String choix) {
    this.choix = choix;
  }

  public List getEtiquetteparametragedemandeList() {
    return etiquetteparametragedemandeList;
  }

  public void setEtiquetteparametragedemandeList(List etiquetteparametragedemandeList) {
    this.etiquetteparametragedemandeList = etiquetteparametragedemandeList;
  }
}

