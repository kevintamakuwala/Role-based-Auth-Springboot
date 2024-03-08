/*
 * Author: Kevin Tamakuwala (21ITUBS120)
 * Modified: 8th March 2024 9:42 PM
 * Purpose: User Response
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
public class UserRes {

    private Long id;
    private String email;
    private String fullName;
    @Enumerated(EnumType.STRING)
    private RoleEnum roleName;

}
