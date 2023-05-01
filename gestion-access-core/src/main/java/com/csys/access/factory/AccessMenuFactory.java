package com.csys.access.factory;

import com.csys.access.domain.AccessMenu;
import com.csys.access.dto.AccessMenuDTO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AccessMenuFactory {

    public static AccessMenuDTO accessmenuToAccessMenuDTO(AccessMenu accessmenu) {
        AccessMenuDTO accessmenuDTO = new AccessMenuDTO();
        accessmenuDTO.setCodeMenu(accessmenu.getAccessMenuPK().getCodeMenu());
        accessmenuDTO.setCodeModule(accessmenu.getAccessMenuPK().getCodeModule());
        accessmenuDTO.setGroupe(accessmenu.getAccessMenuPK().getGroupe());
        accessmenuDTO.setVisible(accessmenu.getVisible()); 
        accessmenuDTO.setMenu(MenuFactory.lazymenuToMenuDTO(accessmenu.getMenu()));
        accessmenuDTO.setGroupeUser(GroupeUserFactory.groupeuserToGroupeUserDTO(accessmenu.getGroupe()));
        return accessmenuDTO;
    }

    public static List<AccessMenuDTO> accessmenuToAccessMenuDTOs(Collection<AccessMenu> accessmenus) {
        List<AccessMenuDTO> accessmenusDTO = new ArrayList<>();
        accessmenus.forEach(x -> {
            accessmenusDTO.add(accessmenuToAccessMenuDTO(x));
        });
        return accessmenusDTO;
    }
}
