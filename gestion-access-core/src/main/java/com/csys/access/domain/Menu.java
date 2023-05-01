/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csys.access.domain;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Administrateur
 */
@Entity
@Table(name = "MenuP")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MenuPK menuPK;
    @Basic(optional = false)
    @NotNull
    @Size(max = 200)
    @Column(name = "DesMenuP", nullable = false, length = 200)
    private String designation;
    @Size(max = 50)
    @Column(length = 50)
    private String logo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "menu")
    private Collection<Button> buttonCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "menu")
    private Collection<AccessMenuUser> accessMenuUserCollection;
    @OneToMany(mappedBy = "menu")
    private Collection<Menu> menuCollection;
    @Basic(optional = false)
    @NotNull
    @Column(name = "[order]")
    private Integer order;

    @JoinColumns({
        @JoinColumn(name = "Code_Menu", referencedColumnName = "CodMnP", insertable = false, updatable = false),
        @JoinColumn(name = "Module", referencedColumnName = "Module", insertable = false, updatable = false)
    })
    @ManyToOne(fetch = FetchType.LAZY)
    private Menu menu;
    @JoinColumn(name = "Module", referencedColumnName = "NumModule", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Module module;

    public Menu() {
    }

    public Menu(MenuPK menuPK) {
        this.menuPK = menuPK;
    }

    public Menu(MenuPK menuPK, String designation) {
        this.menuPK = menuPK;
        this.designation = designation;
    }

    public Menu(String code, String codeModule) {
        this.menuPK = new MenuPK(code, codeModule);
    }

    public MenuPK getMenuPK() {
        return menuPK;
    }

    public void setMenuPK(MenuPK menuPK) {
        this.menuPK = menuPK;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Collection<Button> getButtonCollection() {
        return buttonCollection;
    }

    public void setButtonCollection(Collection<Button> buttonCollection) {
        this.buttonCollection = buttonCollection;
    }

    public Collection<AccessMenuUser> getAccessMenuUserCollection() {
        return accessMenuUserCollection;
    }

    public void setAccessMenuUserCollection(Collection<AccessMenuUser> accessMenuUserCollection) {
        this.accessMenuUserCollection = accessMenuUserCollection;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public Collection<Menu> getMenuCollection() {
        return menuCollection;
    }

    public void setMenuCollection(Collection<Menu> menuCollection) {
        this.menuCollection = menuCollection;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (menuPK != null ? menuPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Menu)) {
            return false;
        }
        Menu other = (Menu) object;
        if ((this.menuPK == null && other.menuPK != null) || (this.menuPK != null && !this.menuPK.equals(other.menuPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.csys.access.domain.Menu[ menuPK=" + menuPK + " ]";
    }

}
