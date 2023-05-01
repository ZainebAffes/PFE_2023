package com.csys.access.factory;

import com.csys.access.domain.AccessButton;
import com.csys.access.dto.AccessButtonDTO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AccessButtonFactory {
  public static AccessButtonDTO accessbuttonToAccessButtonDTO(AccessButton accessbutton) {
    AccessButtonDTO accessbuttonDTO=new AccessButtonDTO();
    accessbuttonDTO.setCodeButton(accessbutton.getAccessButtonPK().getCodeButton());
    accessbuttonDTO.setCodeModule(accessbutton.getAccessButtonPK().getCodeModule());    
    accessbuttonDTO.setVisible(accessbutton.getVisible());
    accessbuttonDTO.setButton(ButtonFactory.buttonToButtonDTO(accessbutton.getButton()));
    accessbuttonDTO.setGroupe(GroupeUserFactory.groupeuserToGroupeUserDTO(accessbutton.getGroupe()));
    return accessbuttonDTO;
  }
  
  public static List<AccessButtonDTO> accessbuttonToAccessButtonDTOs(Collection<AccessButton> accessbuttons) {
    List<AccessButtonDTO> accessbuttonsDTO=new ArrayList<>();
    accessbuttons.forEach(x -> {
      accessbuttonsDTO.add(accessbuttonToAccessButtonDTO(x));
    } );
    return accessbuttonsDTO;
  }
}

