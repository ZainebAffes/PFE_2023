package com.csys.access.factory;

import com.csys.access.domain.Button;
import com.csys.access.dto.AccessButtonDTO;
import com.csys.access.dto.ButtonDTO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ButtonFactory {

    public static ButtonDTO buttonToButtonDTO(Button button) {
        ButtonDTO buttonDTO = new ButtonDTO();
        buttonDTO.setCode(button.getButtonPK().getCode());
        buttonDTO.setCodeMenu(button.getCodeMenu());
        buttonDTO.setCodeModule(button.getButtonPK().getCodeModule());
        buttonDTO.setDesignation(button.getDesignation());
        buttonDTO.setLogo(button.getLogo());
        buttonDTO.setOrderButton(button.getOrderButton());
        Collection<AccessButtonDTO> accessButtonUserCollectionDtos = new ArrayList<>();
        button.getAccessButtonUserCollection().forEach(x -> {
            AccessButtonDTO accessbuttonuserDto = AccessButtonUserFactory.accessbuttonuserToAccessButtonUserDTO(x);
            accessButtonUserCollectionDtos.add(accessbuttonuserDto);
        });
        if (buttonDTO.getAccessButtonUserCollection() != null) {
            buttonDTO.getAccessButtonUserCollection().clear();
            buttonDTO.getAccessButtonUserCollection().addAll(accessButtonUserCollectionDtos);
        } else {
            buttonDTO.setAccessButtonUserCollection(accessButtonUserCollectionDtos);
        }      
        return buttonDTO;
    }

    public static Collection<ButtonDTO> buttonToButtonDTOs(Collection<Button> buttons) {
        List<ButtonDTO> buttonsDTO = new ArrayList<>();
        buttons.forEach(x -> {
            buttonsDTO.add(buttonToButtonDTO(x));
        });
        return buttonsDTO;
    }

    public static ButtonDTO lazybuttonToButtonDTO(Button button) {
        ButtonDTO buttonDTO = new ButtonDTO();
        buttonDTO.setCode(button.getButtonPK().getCode());
        buttonDTO.setCodeMenu(button.getCodeMenu());
        buttonDTO.setCodeModule(button.getButtonPK().getCodeModule());
        buttonDTO.setDesignation(button.getDesignation());
        buttonDTO.setLogo(button.getLogo());
        buttonDTO.setOrderButton(button.getOrderButton());
        return buttonDTO;
    }

    public static Collection<ButtonDTO> lazybuttonToButtonDTOs(Collection<Button> buttons) {
        List<ButtonDTO> buttonsDTO = new ArrayList<>();
        buttons.forEach(x -> {
            buttonsDTO.add(lazybuttonToButtonDTO(x));
        });
        return buttonsDTO;
    }
}
