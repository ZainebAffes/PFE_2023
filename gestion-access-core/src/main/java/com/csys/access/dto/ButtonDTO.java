package com.csys.access.dto;

import java.lang.String;
import java.util.Collection;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ButtonDTO {

    
    private String code;
    
    private String codeMenu;
    
    private String codeModule;
    
    @NotNull
    @Size(
            
            max = 200
    )
    private String designation;

    @NotNull
    @Size(
            
            max = 200
    )
    private String designationsec;

    @NotNull
    @Size(
            
            max = 50
    )
    private String logo;

    @Size(
            min = 0,
            max = 50
    )
    private String orderButton;

    private Collection<AccessButtonDTO> accessButtonUserCollection;
    
    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDesignationsec() {
        return designationsec;
    }

    public void setDesignationsec(String designationsec) {
        this.designationsec = designationsec;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getOrderButton() {
        return orderButton;
    }

    public void setOrderButton(String orderButton) {
        this.orderButton = orderButton;
    }

    public Collection<AccessButtonDTO> getAccessButtonUserCollection() {
        return accessButtonUserCollection;
    }

    public void setAccessButtonUserCollection(Collection<AccessButtonDTO> accessButtonUserCollection) {
        this.accessButtonUserCollection = accessButtonUserCollection;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodeMenu() {
        return codeMenu;
    }

    public void setCodeMenu(String codeMenu) {
        this.codeMenu = codeMenu;
    }

    public String getCodeModule() {
        return codeModule;
    }

    public void setCodeModule(String codeModule) {
        this.codeModule = codeModule;
    }
    
}
