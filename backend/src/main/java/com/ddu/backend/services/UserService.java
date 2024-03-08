/*
 * Author: Kevin Tamakuwala (21ITUBS120) 
 * Modified: 8th March 2024 10:45 PM
 * Purpose: UserService is used to create a faculty user
 */
package com.ddu.backend.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ddu.backend.entities.Role;
import com.ddu.backend.entities.RoleEnum;
import com.ddu.backend.entities.User;
import com.ddu.backend.repositories.RoleRepository;
import com.ddu.backend.repositories.UserRepository;
import com.ddu.backend.requests.RegisterUserReq;
import com.ddu.backend.responses.UserRes;

@Service
public class UserService {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserRes createFaculty(RegisterUserReq input) {
        Optional<Role> optionalRole = roleRepository.findByRoleType(RoleEnum.FACULTY);

        if (optionalRole.isEmpty()) {
            return null;
        }

        User user = new User();
        user.setFullName(input.getFullName());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setRole(optionalRole.get());

        user = userRepository.save(user);

        UserRes res = new UserRes();
        res.setId(user.getId());
        res.setEmail(user.getEmail());
        res.setFullName(user.getFullName());
        res.setRoleName(user.getRole().getRoleType());
        return res;
    }

}
