/*
 * Author: Kevin Tamakuwala (21ITUBS120) 
 * Modified: 2nd March 2024 2:38 AM
 * Purpose: This class is used to handle the user related operations
 */

package com.ddu.backend.controllers;

import com.ddu.backend.entities.User;
import com.ddu.backend.responses.UserResponse;
import com.ddu.backend.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/users")
@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserResponse> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();
        
        String labName = (currentUser.getLab() != null) ? currentUser.getLab().getName() : "";

        UserResponse userResponse = new UserResponse()
                .setId(currentUser.getId())
                .setEmail(currentUser.getEmail())
                .setFullName(currentUser.getFullName())
                .setRoleName(currentUser.getRole().getName())
                .setLabName(labName);

        return ResponseEntity.ok(userResponse);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('FACULTY', 'HOD')")
    public ResponseEntity<List<UserResponse>> allUsers() {
        return ResponseEntity.ok(userService.allUsers());
    }
}
