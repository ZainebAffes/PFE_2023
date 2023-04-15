/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.csys.workflowDemande.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author zeineb
 */
@Entity
@Table(name = "Demande")
@XmlRootElement

public class Demande implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "numeroDemande")
    private Integer numeroDemande;
    @Size(max = 50)
    @Column(name = "designation")
    private String designation;
    @Column(name = "dateCreation")
    @Temporal(TemporalType.DATE)
    private Date dateCreation;
    @Size(max = 50)
    @Column(name = "code_parametrage_demande")
    private String codeParametrageDemande;
    @JoinColumn(name = "id_employe", referencedColumnName = "idEmploye" ,updatable = false, insertable = false, nullable = true)
    @ManyToOne
    private Employe idEmploye;
    @JoinColumn(name = "type_demande", referencedColumnName = "id")
    @ManyToOne
    private EtatValidation typeDemande;
    @JoinColumn(name = "id_employe", referencedColumnName = "code",updatable = false, insertable = false, nullable = true)
    @ManyToOne
    private ParametrageDemande idEmploye1;
    @JoinColumn(name = "type_demande", referencedColumnName = "codeTypeDemande",updatable = false, insertable = false, nullable = true)
    @ManyToOne
    private TypeDemande typeDemande1;

    public Demande() {
    }

    public Demande(Integer numeroDemande) {
        this.numeroDemande = numeroDemande;
    }

    public Integer getNumeroDemande() {
        return numeroDemande;
    }

    public void setNumeroDemande(Integer numeroDemande) {
        this.numeroDemande = numeroDemande;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getCodeParametrageDemande() {
        return codeParametrageDemande;
    }

    public void setCodeParametrageDemande(String codeParametrageDemande) {
        this.codeParametrageDemande = codeParametrageDemande;
    }

    public Employe getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(Employe idEmploye) {
        this.idEmploye = idEmploye;
    }

    public EtatValidation getTypeDemande() {
        return typeDemande;
    }

    public void setTypeDemande(EtatValidation typeDemande) {
        this.typeDemande = typeDemande;
    }

    public ParametrageDemande getIdEmploye1() {
        return idEmploye1;
    }

    public void setIdEmploye1(ParametrageDemande idEmploye1) {
        this.idEmploye1 = idEmploye1;
    }

    public TypeDemande getTypeDemande1() {
        return typeDemande1;
    }

    public void setTypeDemande1(TypeDemande typeDemande1) {
        this.typeDemande1 = typeDemande1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numeroDemande != null ? numeroDemande.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Demande)) {
            return false;
        }
        Demande other = (Demande) object;
        if ((this.numeroDemande == null && other.numeroDemande != null) || (this.numeroDemande != null && !this.numeroDemande.equals(other.numeroDemande))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.csys.workflowDemande.domain.Demande[ numeroDemande=" + numeroDemande + " ]";
    }
    
}
