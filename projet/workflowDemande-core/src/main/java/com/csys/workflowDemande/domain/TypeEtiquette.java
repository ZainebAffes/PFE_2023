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
 * @author zeineb
 */
@Entity
@Table(name = "type_etiquette")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TypeEtiquette.findAll", query = "SELECT t FROM TypeEtiquette t"),
    @NamedQuery(name = "TypeEtiquette.findByCode", query = "SELECT t FROM TypeEtiquette t WHERE t.code = :code"),
    @NamedQuery(name = "TypeEtiquette.findByType", query = "SELECT t FROM TypeEtiquette t WHERE t.type = :type"),
    @NamedQuery(name = "TypeEtiquette.findByLogo", query = "SELECT t FROM TypeEtiquette t WHERE t.logo = :logo")})
public class TypeEtiquette implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "code")
    private Integer code;
    @Size(max = 50)
    @Column(name = "type")
    private String type;
    @Size(max = 10)
    @Column(name = "logo")
    private String logo;
    @OneToMany(mappedBy = "codeTypeEtiquette")
    private List<Etiquetteparametragedemande> etiquetteparametragedemandeList;

    public TypeEtiquette() {
    }

    public TypeEtiquette(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @XmlTransient
    public List<Etiquetteparametragedemande> getEtiquetteparametragedemandeList() {
        return etiquetteparametragedemandeList;
    }

    public void setEtiquetteparametragedemandeList(List<Etiquetteparametragedemande> etiquetteparametragedemandeList) {
        this.etiquetteparametragedemandeList = etiquetteparametragedemandeList;
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
        if (!(object instanceof TypeEtiquette)) {
            return false;
        }
        TypeEtiquette other = (TypeEtiquette) object;
        if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.csys.workflowDemande.domain.TypeEtiquette[ code=" + code + " ]";
    }
    
}
