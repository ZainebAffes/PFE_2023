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
@Table(name = "Access_Menu_User")
@NamedEntityGraphs({
    @NamedEntityGraph(name = "AccessMenuUser.menu",
            attributeNodes = {
                @NamedAttributeNode("menu")
            })
})
public class AccessMenuUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AccessMenuUserPK accessMenuUserPK;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private boolean visible;
    @JoinColumns({
        @JoinColumn(name = "Menu", referencedColumnName = "CodMnP", nullable = false, insertable = false, updatable = false)
        , @JoinColumn(name = "Module", referencedColumnName = "Module", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Menu menu;
    @JoinColumn(name = "[User]", referencedColumnName = "UserName", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;

    public AccessMenuUser() {
    }

    public AccessMenuUser(AccessMenuUserPK accessMenuUserPK) {
        this.accessMenuUserPK = accessMenuUserPK;
    }

    public AccessMenuUser(AccessMenuUserPK accessMenuUserPK, boolean visible) {
        this.accessMenuUserPK = accessMenuUserPK;
        this.visible = visible;
    }

    public AccessMenuUser(String codeMenu, String codeModule, String user) {
        this.accessMenuUserPK = new AccessMenuUserPK(codeMenu, codeModule, user);
    }

    public AccessMenuUserPK getAccessMenuUserPK() {
        return accessMenuUserPK;
    }

    public void setAccessMenuUserPK(AccessMenuUserPK accessMenuUserPK) {
        this.accessMenuUserPK = accessMenuUserPK;
    }

    public boolean getVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
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
        hash += (accessMenuUserPK != null ? accessMenuUserPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccessMenuUser)) {
            return false;
        }
        AccessMenuUser other = (AccessMenuUser) object;
        if ((this.accessMenuUserPK == null && other.accessMenuUserPK != null) || (this.accessMenuUserPK != null && !this.accessMenuUserPK.equals(other.accessMenuUserPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.csys.access.domain.AccessMenuUser[ accessMenuUserPK=" + accessMenuUserPK + " ]";
    }

}
