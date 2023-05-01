/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csys.access.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Administrateur
 */
@Entity
@Table(name = "ACCESS_FORM")
public class AccessButton implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AccessButtonPK accessButtonPK;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private boolean visible;
    @JoinColumns({
        @JoinColumn(name = "Control", referencedColumnName = "Control", nullable = false, insertable = false, updatable = false)
        , @JoinColumn(name = "Form", referencedColumnName = "Form", nullable = false, insertable = false, updatable = false)
        , @JoinColumn(name = "Module", referencedColumnName = "Module", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Button button;
    @JoinColumn(name = "Grp", referencedColumnName = "Grp", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private GroupeUser groupe;

    public AccessButton() {
    }

    public AccessButton(AccessButtonUserPK accessButtonUserPK) {
        this.accessButtonPK = accessButtonPK;
    }

    public AccessButton(AccessButtonUserPK accessButtonUserPK, boolean visible) {
        this.accessButtonPK = accessButtonPK;
        this.visible = visible;
    }



    public AccessButtonPK getAccessButtonPK() {
        return accessButtonPK;
    }

    public void setAccessButtonPK(AccessButtonPK accessButtonPK) {
        this.accessButtonPK = accessButtonPK;
    }

  

    public boolean getVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public GroupeUser getGroupe() {
        return groupe;
    }

    public void setGroupe(GroupeUser groupe) {
        this.groupe = groupe;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accessButtonPK != null ? accessButtonPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccessButton)) {
            return false;
        }
        AccessButton other = (AccessButton) object;
        if ((this.accessButtonPK == null && other.accessButtonPK != null) || (this.accessButtonPK != null && !this.accessButtonPK.equals(other.accessButtonPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.csys.access.domain.AccessButtonUser[ accessButtonPK=" + accessButtonPK + " ]";
    }
    
}
