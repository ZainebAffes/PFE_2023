package com.csys.workflowDemande.dto;

import com.csys.workflowDemande.domain.OptionEtiquette;
import com.csys.workflowDemande.domain.TypeEtiquette;
import java.lang.Double;
import java.lang.Integer;
import java.lang.String;
import java.util.List;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EtiquetteparametragedemandeDTO {

    @NotNull
    private Integer code;

    @Size(
            min = 0,
            max = 50
    )
    private String description;

    private Double min;

    private Double max;

    @Size(
            min = 0,
            max = 50
    )
    private String isRequired;

    @Size(
            min = 0,
            max = 10
    )
    private String position;

    @Size(
            min = 0,
            max = 10
    )
    private String defultValue;

    @Size(
            min = 0,
            max = 10
    )
    private String visible;

    @Size(
            min = 0,
            max = 10
    )
    private String multiple;

    private List<OptionEtiquetteDTO> optionEtiquetteDTOs;

    private TypeEtiquetteDTO typeEtiquetteDTO;

    private ParametrageDemandeDTO parametrageDemande;
    private Integer codeTypeEtiquette;
    private Integer code_parametrage_etiquette;
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

    public List<OptionEtiquetteDTO> getOptionEtiquetteDTOs() {
        return optionEtiquetteDTOs;
    }

    public void setOptionEtiquetteDTOs(List<OptionEtiquetteDTO> optionEtiquetteDTOs) {
        this.optionEtiquetteDTOs = optionEtiquetteDTOs;
    }

    public TypeEtiquetteDTO getTypeEtiquetteDTO() {
        return typeEtiquetteDTO;
    }

    public void setTypeEtiquetteDTO(TypeEtiquetteDTO typeEtiquetteDTO) {
        this.typeEtiquetteDTO = typeEtiquetteDTO;
    }

    public ParametrageDemandeDTO getParametrageDemande() {
        return parametrageDemande;
    }

    public void setParametrageDemande(ParametrageDemandeDTO parametrageDemande) {
        this.parametrageDemande = parametrageDemande;
    }

    public Integer getCodeTypeEtiquette() {
        return codeTypeEtiquette;
    }

    public void setCodeTypeEtiquette(Integer codeTypeEtiquette) {
        this.codeTypeEtiquette = codeTypeEtiquette;
    }

    public Integer getCode_parametrage_etiquette() {
        return code_parametrage_etiquette;
    }

    public void setCode_parametrage_etiquette(Integer code_parametrage_etiquette) {
        this.code_parametrage_etiquette = code_parametrage_etiquette;
    }
    
}
