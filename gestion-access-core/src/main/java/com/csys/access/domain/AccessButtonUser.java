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
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Administrateur
 */
@Entity
@Table(name = "ACCESS_FORM_USER")
@NamedEntityGraphs({
    @NamedEntityGraph(name = "AccessButtonUser.button",
            attributeNodes = {
                @NamedAttributeNode("button")
            })
})
public class AccessButtonUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AccessButtonUserPK accessButtonUserPK;
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
    @JoinColumn(name = "[user]", referencedColumnName = "UserName", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;

    public AccessButtonUser() {
    }

    public AccessButtonUser(AccessButtonUserPK accessButtonUserPK) {
        this.accessButtonUserPK = accessButtonUserPK;
    }

    public AccessButtonUser(AccessButtonUserPK accessButtonUserPK, boolean visible) {
        this.accessButtonUserPK = accessButtonUserPK;
        this.visible = visible;
    }

 

    public AccessButtonUserPK getAccessButtonUserPK() {
        return accessButtonUserPK;
    }

    public void setAccessButtonUserPK(AccessButtonUserPK accessButtonUserPK) {
        this.accessButtonUserPK = accessButtonUserPK;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accessButtonUserPK != null ? accessButtonUserPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccessButtonUser)) {
            return false;
        }
        AccessButtonUser other = (AccessButtonUser) object;
        if ((this.accessButtonUserPK == null && other.accessButtonUserPK != null) || (this.accessButtonUserPK != null && !this.accessButtonUserPK.equals(other.accessButtonUserPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.csys.access.domain.AccessButtonUser[ accessButtonUserPK=" + accessButtonUserPK + " ]";
    }

}
