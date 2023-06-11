
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
   @Basic(optional = false)
    @NotNull
    @Size( max = 50)
    @Id
    @Column(name = "UserName")
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
}
