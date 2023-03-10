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
@Table(name = "option_etiquette")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OptionEtiquette.findAll", query = "SELECT o FROM OptionEtiquette o"),
    @NamedQuery(name = "OptionEtiquette.findByCodeOption", query = "SELECT o FROM OptionEtiquette o WHERE o.codeOption = :codeOption"),
    @NamedQuery(name = "OptionEtiquette.findByChoix", query = "SELECT o FROM OptionEtiquette o WHERE o.choix = :choix")})
public class OptionEtiquette implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "code_option")
    private Integer codeOption;
    @Size(max = 10)
    @Column(name = "choix")
    private String choix;
    @OneToMany(mappedBy = "optionEtiquette")
    private List<Etiquetteparametragedemande> etiquetteparametragedemandeList;

    public OptionEtiquette() {
    }

    public OptionEtiquette(Integer codeOption) {
        this.codeOption = codeOption;
    }

    public Integer getCodeOption() {
        return codeOption;
    }

    public void setCodeOption(Integer codeOption) {
        this.codeOption = codeOption;
    }

    public String getChoix() {
        return choix;
    }

    public void setChoix(String choix) {
        this.choix = choix;
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
        hash += (codeOption != null ? codeOption.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OptionEtiquette)) {
            return false;
        }
        OptionEtiquette other = (OptionEtiquette) object;
        if ((this.codeOption == null && other.codeOption != null) || (this.codeOption != null && !this.codeOption.equals(other.codeOption))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.csys.workflowDemande.domain.OptionEtiquette[ codeOption=" + codeOption + " ]";
    }
    
}
