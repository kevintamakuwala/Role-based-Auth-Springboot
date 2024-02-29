/*
 * Author: Kevin Tamakuwala (21ITUBS120) 
 * Modified: 29th February 2024 1:30 AM
 * Purpose: RegisterUserDto is used to transfer the register user data
 */
package com.ddu.backend.dtos;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RegisterUserDto {
    private String email;
    private String password;
    private String fullName;

    public RegisterUserDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public RegisterUserDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public RegisterUserDto setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }
}
