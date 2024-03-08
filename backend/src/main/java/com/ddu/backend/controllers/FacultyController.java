/*
 * Author: Kevin Tamakuwala (21ITUBS120)
 * Modified: 8th March 2024 9:37 PM
 *  Purpose: This class is used to handle the faculty related operations.
*/
package com.ddu.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ddu.backend.requests.RegisterUserReq;
import com.ddu.backend.responses.UserRes;
import com.ddu.backend.services.UserService;

@RequestMapping("/faculty")
@RestController
public class FacultyController {

    @Autowired
    private UserService userService;

    // Only HOD can create a faculty
    @PreAuthorize("hasRole('HOD')")
    @PostMapping
    public ResponseEntity<UserRes> createFaculty(@RequestBody RegisterUserReq input) {
        UserRes user = userService.createFaculty(input);
        return ResponseEntity.ok(user);
    }
}
