
package com.csys.workflowDemande.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
 * @author win10
 */
@Entity
@Table(name = "Employe")

public class Employe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "idEmploye")
    private String idEmploye;
    @Size(max = 50)
    @Column(name = "nom")
    private String nom;
    @Size(max = 50)
    @Column(name = "prenom")
    private String prenom;
    @Size(max = 50)
    @Column(name = "mail")
    private String mail;
    @Size(max = 50)
    @Column(name = "mot_de_Passe")
    private String motdePasse;
    @Size(max = 50)
    @Column(name = "numTel")
    private String numTel;
    @OneToMany(mappedBy = "idEmploye")
    private List<Demande> demandeList;
    @JoinColumn(name = "groupeEmploye", referencedColumnName = "code")
    @ManyToOne
    private GroupeEmploye groupeEmploye;
    @JoinColumn(name = "TypeEmploy\u00e9", referencedColumnName = "id")
    @ManyToOne
    private TypeEmploye typeEmployé;

    public Employe() {
    }

    public Employe(String idEmploye) {
        this.idEmploye = idEmploye;
    }

    public String getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(String idEmploye) {
        this.idEmploye = idEmploye;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMotdePasse() {
        return motdePasse;
    }

    public void setMotdePasse(String motdePasse) {
        this.motdePasse = motdePasse;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    @XmlTransient
    public List<Demande> getDemandeList() {
        return demandeList;
    }

    public void setDemandeList(List<Demande> demandeList) {
        this.demandeList = demandeList;
    }

    public GroupeEmploye getGroupeEmploye() {
        return groupeEmploye;
    }

    public void setGroupeEmploye(GroupeEmploye groupeEmploye) {
        this.groupeEmploye = groupeEmploye;
    }

    public TypeEmploye getTypeEmployé() {
        return typeEmployé;
    }

    public void setTypeEmployé(TypeEmploye typeEmployé) {
        this.typeEmployé = typeEmployé;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmploye != null ? idEmploye.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employe)) {
            return false;
        }
        Employe other = (Employe) object;
        if ((this.idEmploye == null && other.idEmploye != null) || (this.idEmploye != null && !this.idEmploye.equals(other.idEmploye))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.csys.workflowDemande.domain.Empolye[ idEmploye=" + idEmploye + " ]";
    }
    
}