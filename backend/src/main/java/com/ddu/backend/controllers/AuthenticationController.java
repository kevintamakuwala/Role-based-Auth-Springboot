/*
 * Author: Kevin Tamakuwala (21ITUBS120) 
 * Modified: 3rd March 2024 20:59 AM
 * Purpose: This class is used to handle the authentication of the user
 */
package com.ddu.backend.controllers;

import com.ddu.backend.dtos.LoginUserDto;
import com.ddu.backend.dtos.RegisterUserDto;
import com.ddu.backend.entities.RoleEnum;
import com.ddu.backend.entities.User;
import com.ddu.backend.responses.LoginResponse;
import com.ddu.backend.responses.UserResponse;
import com.ddu.backend.services.AuthenticationService;
import com.ddu.backend.services.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup/{role}")
    public ResponseEntity<UserResponse> register(@RequestBody RegisterUserDto registerUserDto,
            @PathVariable String role) {

        User registeredUser = authenticationService.signup(registerUserDto, RoleEnum.valueOf(role));

        UserResponse userResponse = new UserResponse()
                .setEmail(registeredUser.getEmail())
                .setFullName(registeredUser.getFullName())
                .setId(registeredUser.getId())
                .setLabName(
                        registeredUser.getLab() != null
                                ? registeredUser.getLab().getName()
                                : "")
                .setRoleName(RoleEnum.valueOf(role));

        return ResponseEntity.ok(userResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken)
                .setExpiresIn(jwtService.getExpirationTime())
                .setRole(authenticatedUser.getRole().getName());

        return ResponseEntity.ok(loginResponse);
    }
}