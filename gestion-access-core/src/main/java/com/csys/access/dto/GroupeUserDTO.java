package com.csys.access.dto;

import java.lang.String;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class GroupeUserDTO {
  @NotNull
  @Size(
      
      max = 15
  )
  private String groupe;

  @Size(
      min = 0,
      max = 60
  )
  private String description;

  public String getGroupe() {
    return groupe;
  }

  public void setGroupe(String groupe) {
    this.groupe = groupe;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

}

