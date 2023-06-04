package com.csys.workflowDemande.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author win10
 */
@Entity
@Table(name = "Demande")
public class Demande implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "code")
    private Integer code;
    @Size(max = 50)
    @Column(name = "designation")
    private String designation;
    @Column(name = "dateCreation")
    private LocalDateTime dateCreation;
    @Column(name = "etat")
    private String etats;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "id_Employe")
    private String idEmployes;
    
   
    @Column(name = "code_parametrage_demande")
    private Integer codeParametrageDemandes;

    @JoinColumn(name = "id_employe", referencedColumnName = "idEmploye", updatable = false, insertable = false, nullable = true)
    @ManyToOne
    private Employe idEmploye;
    
    @JoinColumn(name = "code_parametrage_demande", referencedColumnName = "code", updatable = false, insertable = false, nullable = true)
    @ManyToOne
    private ParametrageDemande codeParametrageDemande;
    
    @JoinColumn(name = "etat", referencedColumnName = "code", updatable = false, insertable = false, nullable = true)
    @ManyToOne
    private Etat etat;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "code_champs", referencedColumnName = "code")
    private List<Champs> champses;

    public Demande() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List<Champs>  getChampses() {
        return champses;
    }

    public void setChampses(List<Champs> champses) {
        this.champses = champses;
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

    public Employe getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(Employe idEmploye) {
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

    public String getIdEmployes() {
        return idEmployes;
    }

    public void setIdEmployes(String idEmployes) {
        this.idEmployes = idEmployes;
    }

    public Integer getCodeParametrageDemandes() {
        return codeParametrageDemandes;
    }

    public void setCodeParametrageDemandes(Integer codeParametrageDemandes) {
        this.codeParametrageDemandes = codeParametrageDemandes;
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
        if (!(object instanceof Demande)) {
            return false;
        }
        Demande other = (Demande) object;
        if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.csys.workflowDemande.domain.Demande[ numeroDemande=" + code + " ]";
    }

    

}
