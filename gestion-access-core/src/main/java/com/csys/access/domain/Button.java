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
@Table(name = "Form")
public class Button implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ButtonPK buttonPK;
    @Basic(optional = false)
    @NotNull
    @Size(max = 200)
    @Column(name = "NomControl", nullable = false, length = 200)
    private String designation;
    @Basic(optional = false)
    @NotNull
    @Size(max = 50)
    @Column(nullable = false, length = 50)
    private String logo;
    @Basic(optional = false)
    @NotNull
    @Size(max = 50)
    @Column(name = "code_menu", nullable = false, length = 50)
    private String codeMenu;
    @Size(max = 50)
    @Column(name = "order_button", length = 50)
    private String orderButton;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "button")
    private Collection<AccessButtonUser> accessButtonUserCollection;
    @JoinColumns({
        @JoinColumn(name = "code_menu", referencedColumnName = "CodMnP", nullable = false, insertable = false, updatable = false)
        , @JoinColumn(name = "Module", referencedColumnName = "Module", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Menu menu;

    public Button() {
    }

    public Button(ButtonPK buttonPK) {
        this.buttonPK = buttonPK;
    }

    public Button(ButtonPK buttonPK, String designtion, String logo) {
        this.buttonPK = buttonPK;
        this.designation = designtion;
        this.logo = logo;
    }



    public ButtonPK getButtonPK() {
        return buttonPK;
    }

    public void setButtonPK(ButtonPK buttonPK) {
        this.buttonPK = buttonPK;
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

    public String getCodeMenu() {
        return codeMenu;
    }

    public void setCodeMenu(String codeMenu) {
        this.codeMenu = codeMenu;
    }

    public String getOrderButton() {
        return orderButton;
    }

    public void setOrderButton(String orderButton) {
        this.orderButton = orderButton;
    }

    public Collection<AccessButtonUser> getAccessButtonUserCollection() {
        return accessButtonUserCollection;
    }

    public void setAccessButtonUserCollection(Collection<AccessButtonUser> accessButtonUserCollection) {
        this.accessButtonUserCollection = accessButtonUserCollection;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (buttonPK != null ? buttonPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Button)) {
            return false;
        }
        Button other = (Button) object;
        if ((this.buttonPK == null && other.buttonPK != null) || (this.buttonPK != null && !this.buttonPK.equals(other.buttonPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.csys.access.domain.Button[ buttonPK=" + buttonPK + " ]";
    }

}
