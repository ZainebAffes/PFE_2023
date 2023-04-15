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
@Table(name = "TypeEmploy\u00e9")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TypeEmploy\u00e9.findAll", query = "SELECT t FROM TypeEmploy\u00e9 t"),
    @NamedQuery(name = "TypeEmploy\u00e9.findById", query = "SELECT t FROM TypeEmploy\u00e9 t WHERE t.id = :id"),
    @NamedQuery(name = "TypeEmploy\u00e9.findByDesignation", query = "SELECT t FROM TypeEmploy\u00e9 t WHERE t.designation = :designation")})
public class TypeEmployé implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "id")
    private String id;
    @Size(max = 50)
    @Column(name = "designation")
    private String designation;
    @OneToMany(mappedBy = "typeEmploy\u00e9")
    private List<Employe> employéList;

    public TypeEmployé() {
    }

    public TypeEmployé(String id) {
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
    public List<Employe> getEmployéList() {
        return employéList;
    }

    public void setEmployéList(List<Employe> employéList) {
        this.employéList = employéList;
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
        if (!(object instanceof TypeEmployé)) {
            return false;
        }
        TypeEmployé other = (TypeEmployé) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.csys.workflowDemande.domain.TypeEmploy\u00e9[ id=" + id + " ]";
    }
    
}
