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
 * @author win10
 */
@Entity
@Table(name = "Type_Employe")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TypeEmploye.findAll", query = "SELECT t FROM TypeEmploye t"),
    @NamedQuery(name = "TypeEmploye.findById", query = "SELECT t FROM TypeEmploye t WHERE t.id = :id"),
    @NamedQuery(name = "TypeEmploye.findByDesignation", query = "SELECT t FROM TypeEmploye t WHERE t.designation = :designation")})
public class TypeEmploye implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "id")
    private String id;
    @Size(max = 50)
    @Column(name = "designation")
    private String designation;
    @OneToMany(mappedBy = "typeEmploy\u00e9")
    private List<Empolye> empolyeList;

    public TypeEmploye() {
    }

    public TypeEmploye(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    @XmlTransient
    public List<Empolye> getEmpolyeList() {
        return empolyeList;
    }

    public void setEmpolyeList(List<Empolye> empolyeList) {
        this.empolyeList = empolyeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TypeEmploye)) {
            return false;
        }
        TypeEmploye other = (TypeEmploye) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.csys.workflowDemande.domain.TypeEmploye[ id=" + id + " ]";
    }
    
}
