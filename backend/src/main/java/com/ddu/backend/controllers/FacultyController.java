/*
 * Author: Kevin Tamakuwala (21ITUBS120) 
 * Modified: 29th February 2024 1:30 AM
 * Purpose: This class is used to handle the faculty related operations
 */
package com.ddu.backend.controllers;

import com.ddu.backend.dtos.RegisterUserDto;
import com.ddu.backend.responses.UserResponse;
import com.ddu.backend.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/faculty")
@Controller
public class FacultyController {
    private final UserService userService;

    public FacultyController(UserService userService) {
        this.userService = userService;
    }

    // Only HOD can create a faculty

    @PreAuthorize("hasRole('HOD')")
    @PostMapping
    public ResponseEntity<UserResponse> createFaculty(@RequestBody RegisterUserDto input) {
        UserResponse user = userService.createFaculty(input);
        return ResponseEntity.ok(user);
    }
    
}
