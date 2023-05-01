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
public class AccessMenuUserPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(max = 50)
    @Column(name = "Menu", nullable = false, length = 50)
    private String codeMenu;
    @Basic(optional = false)
    @NotNull
    @Size(max = 3)
    @Column(name = "Module", nullable = false, length = 3)
    private String codeModule;
    @Basic(optional = false)
    @NotNull
    @Size(max = 20)
    @Column(name = "[User]", nullable = false, length = 20)
    private String user;

    public AccessMenuUserPK() {
    }

    public AccessMenuUserPK(String codeMenu, String codeModule, String user) {
        this.codeMenu = codeMenu;
        this.codeModule = codeModule;
        this.user = user;
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codeMenu != null ? codeMenu.hashCode() : 0);
        hash += (codeModule != null ? codeModule.hashCode() : 0);
        hash += (user != null ? user.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccessMenuUserPK)) {
            return false;
        }
        AccessMenuUserPK other = (AccessMenuUserPK) object;
        if ((this.codeMenu == null && other.codeMenu != null) || (this.codeMenu != null && !this.codeMenu.equals(other.codeMenu))) {
            return false;
        }
        if ((this.codeModule == null && other.codeModule != null) || (this.codeModule != null && !this.codeModule.equals(other.codeModule))) {
            return false;
        }
        if ((this.user == null && other.user != null) || (this.user != null && !this.user.equals(other.user))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.csys.access.domain.AccessMenuUserPK[ codeMenu=" + codeMenu + ", codeModule=" + codeModule + ", user=" + user + " ]";
    }

}
