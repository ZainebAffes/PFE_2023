package com.csys.workflowDemande.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
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
 * @author win10
 */
@Entity
@Table(name = "Demande")
public class Demande implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "numeroDemande")
    private String numeroDemande;
    @Size(max = 50)
    @Column(name = "designation")
    private String designation;
    @Column(name = "dateCreation")
    private LocalDateTime dateCreation;
       @Column(name = "etat")
    private String etats;
    @JoinColumn(name = "id_employe", referencedColumnName = "idEmploye", updatable = false, insertable = false, nullable = true)
    @ManyToOne
    private Empolye idEmploye;
    @JoinColumn(name = "code_parametrage_demande", referencedColumnName = "code", updatable = false, insertable = false, nullable = true)
    @ManyToOne
    private ParametrageDemande codeParametrageDemande;
    @JoinColumn(name = "etat", referencedColumnName = "id", updatable = false, insertable = false, nullable = true)
    @ManyToOne
    private Etat etat;

    public Demande() {
    }

    public Demande(String numeroDemande) {
        this.numeroDemande = numeroDemande;
    }

    public String getNumeroDemande() {
        return numeroDemande;
    }

    public void setNumeroDemande(String numeroDemande) {
        this.numeroDemande = numeroDemande;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

   

    public Empolye getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(Empolye idEmploye) {
        this.idEmploye = idEmploye;
    }

    public ParametrageDemande getCodeParametrageDemande() {
        return codeParametrageDemande;
    }

    public void setCodeParametrageDemande(ParametrageDemande codeParametrageDemande) {
        this.codeParametrageDemande = codeParametrageDemande;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public String getEtats() {
        return etats;
    }

    public void setEtats(String etats) {
        this.etats = etats;
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
