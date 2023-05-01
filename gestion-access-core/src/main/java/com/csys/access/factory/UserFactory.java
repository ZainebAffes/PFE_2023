/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csys.access.factory;

import com.csys.access.domain.User;
import com.csys.access.dto.UserDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class UserFactory {

    public static UserDTO userToUserDTO(User user) {
        if (user == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setDescription(user.getDescription());
//        userDTO.setPassword(user.getPassword());
        userDTO.setGroupe(user.getGroupe());
        return userDTO;
    }

    public static User userDTOToUser(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setDescription(userDTO.getDescription());
        user.setPassword(userDTO.getPassword());
        user.setGroupe(userDTO.getGroupe());
        return user;
    }

    public static List<UserDTO> userToUserDTOs(List<User> users) {
        List<UserDTO> usersDTO = new ArrayList<>();
        users.forEach(x -> {
            usersDTO.add(userToUserDTO(x));
        });
        return usersDTO;
    }
}
