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
public class MenuPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(max = 50)
    @Column(name = "CodMnP", nullable = false, length = 50)
    private String code;
    @Basic(optional = false)
    @NotNull
    @Size(max = 3)
    @Column(name = "Module", nullable = false, length = 3)
    private String codeModule;

    public MenuPK() {
    }

    public MenuPK(String code, String codeModule) {
        this.code = code;
        this.codeModule = codeModule;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
        hash += (code != null ? code.hashCode() : 0);
        hash += (codeModule != null ? codeModule.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MenuPK)) {
            return false;
        }
        MenuPK other = (MenuPK) object;
        if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
            return false;
        }
        if ((this.codeModule == null && other.codeModule != null) || (this.codeModule != null && !this.codeModule.equals(other.codeModule))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.csys.access.domain.MenuPK[ code=" + code + ", codeModule=" + codeModule + " ]";
    }

}
