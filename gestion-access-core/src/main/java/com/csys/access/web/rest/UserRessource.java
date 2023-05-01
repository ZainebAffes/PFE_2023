/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csys.access.web.rest;

/**
 *
 * @author DELL
 */
import com.csys.access.dto.UserDTO;
import com.csys.access.service.UserService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserRessource {

    private final UserService userService;

    public UserRessource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public List<UserDTO> getAllUser() {
        return userService.getAllUser();
    }

    @GetMapping("/user/is-authenticated")
    public UserDTO isAuthenticated() {
        return userService.findUser();
    }
}
