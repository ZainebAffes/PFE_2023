package com.csys.access.dto;

import javax.validation.constraints.NotNull;

public class AccessMenuDTO {

    private String codeMenu;

    private String codeModule;

    private Integer orderMenu;

    private String groupe;
    @NotNull
    private boolean visible;

    private MenuDTO menu;

    private GroupeUserDTO groupeUser;

    private UserDTO user;

    public boolean getVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public MenuDTO getMenu() {
        return menu;
    }

    public void setMenu(MenuDTO menu) {
        this.menu = menu;
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

    public String getGroupe() {
        return groupe;
    }

    public void setGroupe(String groupe) {
        this.groupe = groupe;
    }

    public GroupeUserDTO getGroupeUser() {
        return groupeUser;
    }

    public void setGroupeUser(GroupeUserDTO groupeUser) {
        this.groupeUser = groupeUser;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public Integer getOrderMenu() {
        return orderMenu;
    }

    public void setOrderMenu(Integer orderMenu) {
        this.orderMenu = orderMenu;
    }

    @Override
    public String toString() {
        return "AccessMenuDTO{" + "codeMenu=" + codeMenu + ", codeModule=" + codeModule + ", order_menu=" + orderMenu + ", groupe=" + groupe + ", visible=" + visible + ", menu=" + menu + ", groupeUser=" + groupeUser + ", user1=" + user + '}';
    }

}
