/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csys.access.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Administrateur
 */
@Embeddable
public class AccessMenuPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size( max = 50)
    @Column(name = "MENU", nullable = false, length = 50)
    private String codeMenu;
    @Basic(optional = false)
    @NotNull
    @Size( max = 3)
    @Column(name = "Module", nullable = false, length = 3)
    private String codeModule;
    @Basic(optional = false)
    @NotNull
    @Size( max = 20)
    @Column(name = "Grp",nullable = false, length = 20)
    private String groupe;

    public String getGroupe() {
        return groupe;
    }

    public void setGroupe(String groupe) {
        this.groupe = groupe;
    }

    public AccessMenuPK() {
    }

    public AccessMenuPK(String codeMenu, String codeModule, String groupe) {
        this.codeMenu = codeMenu;
        this.codeModule = codeModule;
        this.groupe = groupe;
    }

    public String getCodeMenu() {
        return codeMenu;
    }

    public void setCodeMenu(String codeMenu) {
        this.codeMenu = codeMenu;
    }

    public String getCodeModule() {
        return codeModule;
    }

    public void setCodeModule(String codeModule) {
        this.codeModule = codeModule;
    }

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codeMenu != null ? codeMenu.hashCode() : 0);
        hash += (codeModule != null ? codeModule.hashCode() : 0);
        hash += (groupe != null ? groupe.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccessMenuPK)) {
            return false;
        }
        AccessMenuPK other = (AccessMenuPK) object;
        if ((this.codeMenu == null && other.codeMenu != null) || (this.codeMenu != null && !this.codeMenu.equals(other.codeMenu))) {
            return false;
        }
        if ((this.codeModule == null && other.codeModule != null) || (this.codeModule != null && !this.codeModule.equals(other.codeModule))) {
            return false;
        }
        if ((this.groupe == null && other.groupe != null) || (this.groupe != null && !this.groupe.equals(other.groupe))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.csys.access.domain.AccessMenuUserPK[ codeMenu=" + codeMenu + ", codeModule=" + codeModule + ", groupe=" + groupe + " ]";
    }
    
}
