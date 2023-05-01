/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csys.access.dto;

/**
 *
 * @author DELL
 */
public class UserDTO {
    private String username;
    
    private String description;
   
    private String password;
   
    private String groupe;

    public UserDTO(String username, String description, String password, String groupe) {
        this.username = username;
        this.description = description;
        this.password = password;
        this.groupe = groupe;
    }

    public UserDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGroupe() {
        return groupe;
    }

    public void setGroupe(String groupe) {
        this.groupe = groupe;
    }
    
}
