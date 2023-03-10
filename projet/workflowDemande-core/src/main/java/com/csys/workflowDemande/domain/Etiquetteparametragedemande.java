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
 * @author zeineb
 */
@Entity
@Table(name = "Etiquette_parametrage_demande")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Etiquetteparametragedemande.findAll", query = "SELECT e FROM Etiquetteparametragedemande e"),
    @NamedQuery(name = "Etiquetteparametragedemande.findByCode", query = "SELECT e FROM Etiquetteparametragedemande e WHERE e.code = :code"),
    @NamedQuery(name = "Etiquetteparametragedemande.findByDescription", query = "SELECT e FROM Etiquetteparametragedemande e WHERE e.description = :description"),
    @NamedQuery(name = "Etiquetteparametragedemande.findByMin", query = "SELECT e FROM Etiquetteparametragedemande e WHERE e.min = :min"),
    @NamedQuery(name = "Etiquetteparametragedemande.findByMax", query = "SELECT e FROM Etiquetteparametragedemande e WHERE e.max = :max"),
    @NamedQuery(name = "Etiquetteparametragedemande.findByIsRequired", query = "SELECT e FROM Etiquetteparametragedemande e WHERE e.isRequired = :isRequired"),
    @NamedQuery(name = "Etiquetteparametragedemande.findByPosition", query = "SELECT e FROM Etiquetteparametragedemande e WHERE e.position = :position"),
    @NamedQuery(name = "Etiquetteparametragedemande.findByDefultValue", query = "SELECT e FROM Etiquetteparametragedemande e WHERE e.defultValue = :defultValue"),
    @NamedQuery(name = "Etiquetteparametragedemande.findByVisible", query = "SELECT e FROM Etiquetteparametragedemande e WHERE e.visible = :visible"),
    @NamedQuery(name = "Etiquetteparametragedemande.findByMultiple", query = "SELECT e FROM Etiquetteparametragedemande e WHERE e.multiple = :multiple")})
public class Etiquetteparametragedemande implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "code")
    private Integer code;
    @Size(max = 50)
    @Column(name = "description")
    private String description;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "min")
    private Double min;
    @Column(name = "max")
    private Double max;
    @Size(max = 50)
    @Column(name = "isRequired")
    private String isRequired;
    @Size(max = 10)
    @Column(name = "position")
    private String position;
    @Size(max = 10)
    @Column(name = "defultValue")
    private String defultValue;
    @Size(max = 10)
    @Column(name = "visible")
    private String visible;
    @Size(max = 10)
    @Column(name = "multiple")
    private String multiple;
    @JoinColumn(name = "option_etiquette", referencedColumnName = "code_option")
    @ManyToOne
    private OptionEtiquette optionEtiquette;
    @JoinColumn(name = "code_type_etiquette", referencedColumnName = "code")
    @ManyToOne
    private TypeEtiquette codeTypeEtiquette;
    @OneToMany(mappedBy = "codeParametrageEtiquette")
    private List<ParametrageDemande> parametrageDemandeList;

    public Etiquetteparametragedemande() {
    }

    public Etiquetteparametragedemande(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public String getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(String isRequired) {
        this.isRequired = isRequired;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDefultValue() {
        return defultValue;
    }

    public void setDefultValue(String defultValue) {
        this.defultValue = defultValue;
    }

    public String getVisible() {
        return visible;
    }

    public void setVisible(String visible) {
        this.visible = visible;
    }

    public String getMultiple() {
        return multiple;
    }

    public void setMultiple(String multiple) {
        this.multiple = multiple;
    }

    public OptionEtiquette getOptionEtiquette() {
        return optionEtiquette;
    }

    public void setOptionEtiquette(OptionEtiquette optionEtiquette) {
        this.optionEtiquette = optionEtiquette;
    }

    public TypeEtiquette getCodeTypeEtiquette() {
        return codeTypeEtiquette;
    }

    public void setCodeTypeEtiquette(TypeEtiquette codeTypeEtiquette) {
        this.codeTypeEtiquette = codeTypeEtiquette;
    }

    @XmlTransient
    public List<ParametrageDemande> getParametrageDemandeList() {
        return parametrageDemandeList;
    }

    public void setParametrageDemandeList(List<ParametrageDemande> parametrageDemandeList) {
        this.parametrageDemandeList = parametrageDemandeList;
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
        if (!(object instanceof Etiquetteparametragedemande)) {
            return false;
        }
        Etiquetteparametragedemande other = (Etiquetteparametragedemande) object;
        if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.csys.workflowDemande.domain.Etiquetteparametragedemande[ code=" + code + " ]";
    }
    
}
