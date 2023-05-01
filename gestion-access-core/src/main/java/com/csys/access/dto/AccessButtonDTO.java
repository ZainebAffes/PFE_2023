package com.csys.access.dto;

import javax.validation.constraints.NotNull;

public class AccessButtonDTO {

    private String codeButton;

    private String codeModule;

    private String codeMenu;

    private String user;
    @NotNull
    private boolean visible;

    private ButtonDTO button;

    private GroupeUserDTO groupe;

    private UserDTO userDTO;

    public boolean getVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public String getCodeButton() {
        return codeButton;
    }

    public void setCodeButton(String codeButton) {
        this.codeButton = codeButton;
    }

    public String getCodeModule() {
        return codeModule;
    }

    public void setCodeModule(String codeModule) {
        this.codeModule = codeModule;
    }

    public String getCodeMenu() {
        return codeMenu;
    }

    public void setCodeMenu(String codeMenu) {
        this.codeMenu = codeMenu;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public ButtonDTO getButton() {
        return button;
    }

    public void setButton(ButtonDTO button) {
        this.button = button;
    }

    public GroupeUserDTO getGroupe() {
        return groupe;
    }

    public void setGroupe(GroupeUserDTO groupe) {
        this.groupe = groupe;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

}
