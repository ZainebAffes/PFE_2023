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
public class AccessButtonUserPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(max = 50)
    @Column(name = "Control", nullable = false, length = 50)
    private String codeButton;
    @Basic(optional = false)
    @NotNull
    @Size(max = 3)
    @Column(name = "Module", nullable = false, length = 3)
    private String codeModule;
    @Basic(optional = false)
    @NotNull
    @Size(max = 50)
    @Column(name = "Form", nullable = false, length = 50)
    private String form;
    @Basic(optional = false)
    @NotNull
    @Size(max = 20)
    @Column(name = "[user]", nullable = false, length = 20)
    private String user;

    public AccessButtonUserPK() {
    }

    public String getCodeButton() {
        return codeButton;
    }

    public void setCodeButton(String codeButton) {
        this.codeButton = codeButton;
    }

    public String getCodeModule() {
        return codeModule;
    }

    public void setCodeModule(String codeModule) {
        this.codeModule = codeModule;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
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
        hash += (codeButton != null ? codeButton.hashCode() : 0);
        hash += (codeModule != null ? codeModule.hashCode() : 0);
        hash += (form != null ? form.hashCode() : 0);
        hash += (user != null ? user.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccessButtonUserPK)) {
            return false;
        }
        AccessButtonUserPK other = (AccessButtonUserPK) object;
        if ((this.codeButton == null && other.codeButton != null) || (this.codeButton != null && !this.codeButton.equals(other.codeButton))) {
            return false;
        }
        if ((this.codeModule == null && other.codeModule != null) || (this.codeModule != null && !this.codeModule.equals(other.codeModule))) {
            return false;
        }
        if ((this.form == null && other.form != null) || (this.form != null && !this.form.equals(other.form))) {
            return false;
        }
        if ((this.user == null && other.user != null) || (this.user != null && !this.user.equals(other.user))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.csys.access.domain.AccessButtonUserPK[ codeButton=" + codeButton + ", codeModule=" + codeModule + ", form=" + form + ", user=" + user + " ]";
    }

}
