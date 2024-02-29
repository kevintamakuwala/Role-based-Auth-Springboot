/*
 * Author: Kevin Tamakuwala (21ITUBS120) 
 * Modified: 29th February 2024 1:30 AM
 * Purpose: LoginUserDto is used to transfer the login user data
 */
package com.ddu.backend.dtos;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class LoginUserDto {
    private String email;
    private String password;

    public LoginUserDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public LoginUserDto setPassword(String password) {
        this.password = password;
        return this;
    }
}
