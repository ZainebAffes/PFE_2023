/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.csys.workflowDemande.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author zeineb
 */
@Entity
@Table(name = "parametrage_demande")
public class ParametrageDemande implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "code")
    private Integer code;
    @Size(max = 10)
    @Column(name = "designation")
    private String designation;
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "code_TypeDemande")
    private String codeTypeDemande;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "code_parametrage_etiquette", referencedColumnName = "code")
    private List<Etiquetteparametragedemande> etiquetteparametragedemandes;
    @JoinColumn(name = "code_TypeDemande", referencedColumnName = "codeTypeDemande", updatable = false, insertable = false, nullable = true)
    @ManyToOne
    private TypeDemande typeDemande;

    public ParametrageDemande() {
    }

    public ParametrageDemande(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public List<Etiquetteparametragedemande> getEtiquetteparametragedemandes() {
        return etiquetteparametragedemandes;
    }

    public void setEtiquetteparametragedemandes(List<Etiquetteparametragedemande> etiquetteparametragedemandes) {
        this.etiquetteparametragedemandes = etiquetteparametragedemandes;
    }

    public String getCodeTypeDemande() {
        return codeTypeDemande;
    }

    public void setCodeTypeDemande(String codeTypeDemande) {
        this.codeTypeDemande = codeTypeDemande;
    }

    public TypeDemande getTypeDemande() {
        return typeDemande;
    }

    public void setTypeDemande(TypeDemande typeDemande) {
        this.typeDemande = typeDemande;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (code != null ? code.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParametrageDemande)) {
            return false;
        }
        ParametrageDemande other = (ParametrageDemande) object;
        if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.csys.workflowDemande.domain.ParametrageDemande[ code=" + code + " ]";
    }

}
