/*
 * Author: Kevin Tamakuwala (21ITUBS120) 
 * Modified: 29th February 2024 5:05 PM
 * Purpose: This class is used to define the user service operations
 */
package com.ddu.backend.services;

import com.ddu.backend.dtos.RegisterUserDto;
import com.ddu.backend.entities.Role;
import com.ddu.backend.entities.RoleEnum;
import com.ddu.backend.entities.User;
import com.ddu.backend.repositories.RoleRepository;
import com.ddu.backend.repositories.UserRepository;
import com.ddu.backend.responses.UserResponse;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserResponse> allUsers() {
        List<UserResponse> users = new ArrayList<>();
        List<User> allUsers = userRepository.findAll();

        for (User user : allUsers) {
            String labName = (user.getLab() != null) ? user.getLab().getName() : "";
            RoleEnum roleName = (user.getRole() != null) ? user.getRole().getName() : null;

            users.add(new UserResponse()
                    .setId(user.getId())
                    .setFullName(user.getFullName())
                    .setEmail(user.getEmail())
                    .setLabName(labName)
                    .setRoleName(roleName));
        }
        return users;
    }

    public UserResponse createFaculty(RegisterUserDto input) {
        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.FACULTY);

        if (optionalRole.isEmpty()) {
            return null;
        }

        User user = userRepository.save(new User()
                .setFullName(input.getFullName())
                .setEmail(input.getEmail())
                .setPassword(passwordEncoder.encode(input.getPassword()))
                .setRole(optionalRole.get())
                .setLab(null));
        String labName = (user.getLab() != null) ? user.getLab().getName() : "";
        return new UserResponse()
                .setId(user.getId())
                .setFullName(user.getFullName())
                .setEmail(user.getEmail())
                .setRoleName(user.getRole().getName())
                .setLabName(labName);

    }
}
