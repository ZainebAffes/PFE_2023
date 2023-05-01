package com.csys.access.factory;

import com.csys.access.domain.AccessMenuUser;
import com.csys.access.dto.AccessMenuDTO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AccessMenuUserFactory {

    public static AccessMenuDTO accessmenuuserToAccessMenuUserDTO(AccessMenuUser accessmenuuser) {
        AccessMenuDTO accessmenuuserDTO = new AccessMenuDTO();
        accessmenuuserDTO.setCodeMenu(accessmenuuser.getAccessMenuUserPK().getCodeMenu());
        accessmenuuserDTO.setCodeModule(accessmenuuser.getAccessMenuUserPK().getCodeModule());
        accessmenuuserDTO.setVisible(accessmenuuser.getVisible());
        accessmenuuserDTO.setMenu(MenuFactory.lazymenuToMenuDTO(accessmenuuser.getMenu()));
        accessmenuuserDTO.setUser(UserFactory.userToUserDTO(accessmenuuser.getUser()));
        return accessmenuuserDTO;
    }

    public static Collection<AccessMenuDTO> accessmenuuserToAccessMenuUserDTOs(Collection<AccessMenuUser> accessmenuusers) {
        List<AccessMenuDTO> accessmenuusersDTO = new ArrayList<>();
        accessmenuusers.forEach(x -> {
            accessmenuusersDTO.add(accessmenuuserToAccessMenuUserDTO(x));
        });
        return accessmenuusersDTO;
    }
}
