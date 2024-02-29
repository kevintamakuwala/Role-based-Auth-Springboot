/*
 * Author: Kevin Tamakuwala (21ITUBS120) 
 * Modified: 29th February 2024 5:05 PM
 * Purpose: UserResponse is used to transfer the User data
 */
package com.ddu.backend.responses;

import com.ddu.backend.entities.RoleEnum;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserResponse {

    private Long id;
    private String email;
    private String fullName;
    @Enumerated(EnumType.STRING)
    private RoleEnum roleName;
    private String labName;

    // Setters for UserResponse chaining
    public UserResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public UserResponse setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserResponse setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public UserResponse setRoleName(RoleEnum roleEnum) {
        this.roleName = roleEnum;
        return this;
    }

    public UserResponse setLabName(String labName) {
        this.labName = labName;
        return this;
    }

}
