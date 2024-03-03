/*
 * Author: Kevin Tamakuwala (21ITUBS120) 
 * Modified: 29th February 2024 1:30 AM
 * Purpose: LoginResponse is used to transfer the login response data
 */
package com.ddu.backend.responses;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class LoginResponse {
    private String token;

    private long expiresIn;

    public LoginResponse setToken(String token) {
        this.token = token;
        return this;
    }

    public LoginResponse setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
        return this;
    }
}