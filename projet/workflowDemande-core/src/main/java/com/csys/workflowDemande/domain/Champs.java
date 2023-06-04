/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.csys.workflowDemande.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author zeineb
 */
@Entity
@Table(name = "Liste_Des_Champs")

public class Champs implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Basic(optional = false)
    @Column(name = "code")
    private Integer code;
    @Size(max = 50)

    @Column(name = "nom_champ")
    private String nomChamp;

    @Column(name = "valeur")
    private String valeur;

    @Column(name = "code_champs")
    private String codeDemande;
    
    @JoinColumn(name = "code_champs", referencedColumnName = "code")
    @MapsId("code")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Demande demande;
    
    @NotNull
    @Column(name = "code_type_etiquette")
    private Integer codeTypeEtiquette;
    @JoinColumn(name = "code_type_etiquette", referencedColumnName = "code", updatable = false, insertable = false, nullable = true)
    @ManyToOne
    private TypeEtiquette typeEtiquette;

    public Champs() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getNomChamp() {
        return nomChamp;
    }

    public void setNomChamp(String nomChamp) {
        this.nomChamp = nomChamp;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public Demande getDemande() {
        return demande;
    }

    public void setDemande(Demande demande) {
        this.demande = demande;
    }

    public Integer getCodeTypeEtiquette() {
        return codeTypeEtiquette;
    }

    public void setCodeTypeEtiquette(Integer codeTypeEtiquette) {
        this.codeTypeEtiquette = codeTypeEtiquette;
    }

    public TypeEtiquette getTypeEtiquette() {
        return typeEtiquette;
    }

    public void setTypeEtiquette(TypeEtiquette typeEtiquette) {
        this.typeEtiquette = typeEtiquette;
    }

    public String getCodeDemande() {
        return codeDemande;
    }

    public void setCodeDemande(String codeDemande) {
        this.codeDemande = codeDemande;
    }

    @Override
    public String toString() {
        return "com.csys.workflowDemande.domain.Etiquetteparametragedemande[ code=" + code + " ]";
    }

}
