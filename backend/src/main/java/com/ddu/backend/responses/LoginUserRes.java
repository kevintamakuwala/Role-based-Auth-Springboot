/*
 * Author: Kevin Tamakuwala (21ITUBS120)
 * Modified: 8th March 2024 9:42 PM
 * Purpose: Login User Response
*/
package com.ddu.backend.responses;

import com.ddu.backend.entities.RoleEnum;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class LoginUserRes {

    private String token;

    private long expiresIn;

    @Enumerated(EnumType.STRING)
    private RoleEnum roleName;
}