package com.csys.access.dto;

import java.lang.String;
import java.util.Collection;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class MenuDTO {

    private String code;
    
    private String codeModule;
    
    private Integer orderMenu;
    @NotNull
    @Size(
            max = 200
    )
    private String designation;

    @Size(
            min = 0,
            max = 50
    )
    private String logo;

    @Size(
            min = 0,
            max = 100
    )
    private String designationsec;

    private Collection<ButtonDTO> buttonCollection;

    private Collection<AccessMenuDTO> accessMenuUserCollection;

    private Collection<MenuDTO> menuCollection;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodeModule() {
        return codeModule;
    }

    public void setCodeModule(String codeModule) {
        this.codeModule = codeModule;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getDesignationsec() {
        return designationsec;
    }

    public void setDesignationsec(String designationsec) {
        this.designationsec = designationsec;
    }

    public Collection<ButtonDTO> getButtonCollection() {
        return buttonCollection;
    }

    public void setButtonCollection(Collection<ButtonDTO> buttonCollection) {
        this.buttonCollection = buttonCollection;
    }

    public Collection<AccessMenuDTO> getAccessMenuUserCollection() {
        return accessMenuUserCollection;
    }

    public void setAccessMenuUserCollection(Collection<AccessMenuDTO> accessMenuUserCollection) {
        this.accessMenuUserCollection = accessMenuUserCollection;
    }

    public Collection<MenuDTO> getMenuCollection() {
        return menuCollection;
    }

    public void setMenuCollection(Collection<MenuDTO> menuCollection) {
        this.menuCollection = menuCollection;
    }

    public Integer getOrderMenu() {
        return orderMenu;
    }

    public void setOrderMenu(Integer orderMenu) {
        this.orderMenu = orderMenu;
    }



}
