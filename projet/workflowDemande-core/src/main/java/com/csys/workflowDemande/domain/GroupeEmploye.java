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
@Table(name = "GroupeEmploye")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GroupeEmploye.findAll", query = "SELECT g FROM GroupeEmploye g"),
    @NamedQuery(name = "GroupeEmploye.findByCode", query = "SELECT g FROM GroupeEmploye g WHERE g.code = :code"),
    @NamedQuery(name = "GroupeEmploye.findByDesignation", query = "SELECT g FROM GroupeEmploye g WHERE g.designation = :designation")})
public class GroupeEmploye implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "code")
    private String code;
    @Size(max = 50)
    @Column(name = "designation")
    private String designation;
    @OneToMany(mappedBy = "groupeEmploye")
    private List<Employe> employéList;

    public GroupeEmploye() {
    }

    public GroupeEmploye(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
        hash += (code != null ? code.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GroupeEmploye)) {
            return false;
        }
        GroupeEmploye other = (GroupeEmploye) object;
        if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.csys.workflowDemande.domain.GroupeEmploye[ code=" + code + " ]";
    }
    
}
