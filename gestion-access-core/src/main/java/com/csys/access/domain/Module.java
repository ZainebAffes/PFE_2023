/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csys.access.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Administrateur
 */
@Entity
@Table(name = "Module")
@NamedQueries({
    @NamedQuery(name = "Module.findAll", query = "SELECT m FROM Module m")})
public class Module implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size( max = 3)
    @Column(name = "NumModule")
    private String numModule;
    @Size(max = 100)
    @Column(name = "DesModule")
    private String desModule;
    @Basic(optional = false)
    @NotNull
    @Size( max = 50)
    @Column(name = "Chemin")
    private String chemin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Afficher")
    private boolean afficher;
    @Lob
    @Column(name = "Image")
    private byte[] image;
    @Size(max = 50)
    @Column(name = "NomImage")
    private String nomImage;
    @Basic(optional = false)
    @NotNull
    @Size( max = 50)
    @Column(name = "Version_Database")
    private String versionDatabase;
    @Basic(optional = false)
    @NotNull
    @Size( max = 10)
    @Column(name = "Version_Module")
    private String versionModule;

    public Module() {
    }

    public String getNumModule() {
        return numModule;
    }

    public void setNumModule(String numModule) {
        this.numModule = numModule;
    }

    public String getDesModule() {
        return desModule;
    }

    public void setDesModule(String desModule) {
        this.desModule = desModule;
    }

    

    public String getChemin() {
        return chemin;
    }

    public void setChemin(String chemin) {
        this.chemin = chemin;
    }

    public boolean getAfficher() {
        return afficher;
    }

    public void setAfficher(boolean afficher) {
        this.afficher = afficher;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getNomImage() {
        return nomImage;
    }

    public void setNomImage(String nomImage) {
        this.nomImage = nomImage;
    }

    

    public String getVersionDatabase() {
        return versionDatabase;
    }

    public void setVersionDatabase(String versionDatabase) {
        this.versionDatabase = versionDatabase;
    }

    

    public String getVersionModule() {
        return versionModule;
    }

    public void setVersionModule(String versionModule) {
        this.versionModule = versionModule;
    }
}
