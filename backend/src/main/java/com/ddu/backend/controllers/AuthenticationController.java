/*
 * Author: Kevin Tamakuwala (21ITUBS120)
 * Modified: 8th March 2024 9:37 PM
 *  Purpose: This class is used to handle the authentication of the user.
*/

package com.ddu.backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ddu.backend.entities.RoleEnum;
import com.ddu.backend.entities.User;
import com.ddu.backend.requests.LoginUserReq;
import com.ddu.backend.requests.RegisterUserReq;
import com.ddu.backend.responses.LoginUserRes;
import com.ddu.backend.responses.UserRes;
import com.ddu.backend.services.AuthenticationService;
import com.ddu.backend.services.JwtService;

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
    public ResponseEntity<UserRes> register(@RequestBody RegisterUserReq registerUserReq,
            @PathVariable String role) {

        User registeredUser = authenticationService.signup(registerUserReq, RoleEnum.valueOf(role));

        UserRes userResponse = new UserRes(registeredUser.getId(), registeredUser.getEmail(),
                registeredUser.getFullName(),
                registeredUser.getRole().getRoleType());

        return ResponseEntity.ok(userResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginUserRes> authenticate(@RequestBody LoginUserReq loginUserReq) {
        User authenticatedUser = authenticationService.authenticate(loginUserReq);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginUserRes loginResponse = new LoginUserRes(jwtToken, jwtService.getExpirationTime(),
                authenticatedUser.getRole().getRoleType());

        return ResponseEntity.ok(loginResponse);
    }

}
