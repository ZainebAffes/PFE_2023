package com.csys.workflowDemande.dto;

import java.lang.String;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EtatDTO {

  @Size(
      min = 1,
      max = 50
  )
  private String code;

  @Size(
      min = 0,
      max = 50
  )
  private String designation;

  @Size(
      min = 0,
      max = 255
  )
  private String logo;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

  

  public String getDesignation() {
    return designation;
  }

  public void setDesignation(String designation) {
    this.designation = designation;
  }

  public String getLogo() {
    return logo;
  }

  public void setLogo(String logo) {
    this.logo = logo;
  }
}

