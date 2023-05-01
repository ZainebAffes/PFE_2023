/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csys.access.service;

import com.csys.access.domain.User;
import com.csys.access.dto.UserDTO;
import com.csys.access.factory.UserFactory;
import com.csys.access.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author DELL
 */
@Service
@Transactional
public class UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<UserDTO> getAllUser() {
        List<User> user = userRepository.findAll();
        return UserFactory.userToUserDTOs(user);
    }

    @Transactional(readOnly = true)
    public UserDTO findUserByUsername(String login) {
        User user = userRepository.findOne(login);
        return UserFactory.userToUserDTO(user);

    }
    @Transactional(readOnly = true)
    public UserDTO findUser() {
        User user = userRepository.findOne(SecurityContextHolder.getContext().getAuthentication().getName());
        return UserFactory.userToUserDTO(user);

    }
}
