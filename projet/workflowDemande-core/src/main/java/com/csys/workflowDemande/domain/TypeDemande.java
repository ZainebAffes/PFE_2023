/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.csys.workflowDemande.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "TypeDemande")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TypeDemande.findAll", query = "SELECT t FROM TypeDemande t"),
    @NamedQuery(name = "TypeDemande.findByCodeTypeDemande", query = "SELECT t FROM TypeDemande t WHERE t.codeTypeDemande = :codeTypeDemande"),
    @NamedQuery(name = "TypeDemande.findByDescription", query = "SELECT t FROM TypeDemande t WHERE t.description = :description"),
    @NamedQuery(name = "TypeDemande.findByNom", query = "SELECT t FROM TypeDemande t WHERE t.nom = :nom")})
public class TypeDemande implements Serializable {

    @Size(max = 50)
    @Column(name = "description")
    private String description;
    @Size(max = 50)
    @Column(name = "nom")
    private String nom;
    @OneToMany(mappedBy = "codeTypeDemande")
    private List<ParametrageDemande> parametrageDemandeList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "codeTypeDemande")
    private String codeTypeDemande;

    public TypeDemande() {
    }

    public TypeDemande(String codeTypeDemande) {
        this.codeTypeDemande = codeTypeDemande;
    }

    public String getCodeTypeDemande() {
        return codeTypeDemande;
    }

    public void setCodeTypeDemande(String codeTypeDemande) {
        this.codeTypeDemande = codeTypeDemande;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codeTypeDemande != null ? codeTypeDemande.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TypeDemande)) {
            return false;
        }
        TypeDemande other = (TypeDemande) object;
        if ((this.codeTypeDemande == null && other.codeTypeDemande != null) || (this.codeTypeDemande != null && !this.codeTypeDemande.equals(other.codeTypeDemande))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.csys.workflowDemande.domain.TypeDemande[ codeTypeDemande=" + codeTypeDemande + " ]";
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @XmlTransient
    public List<ParametrageDemande> getParametrageDemandeList() {
        return parametrageDemandeList;
    }

    public void setParametrageDemandeList(List<ParametrageDemande> parametrageDemandeList) {
        this.parametrageDemandeList = parametrageDemandeList;
    }
    
}
