package com.csys.workflowDemande.dto;

import java.lang.String;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Etat {
  @NotNull
  @Size(
      min = 1,
      max = 50
  )
  private String id;

  @Size(
      min = 0,
      max = 255
  )
  private String logo;

  @Size(
      min = 0,
      max = 50
  )
  private String designation;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }


}

