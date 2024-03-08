/*
 * Author: Kevin Tamakuwala (21ITUBS120)
 * Modified: 8th March 2024 9:42 PM
 * Purpose: Permission User Request
*/
package com.ddu.backend.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.util.List;

import com.ddu.backend.entities.PermissionEnum;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class PermissionReq {
    private Long userId;
    private String resourceName;
    private List<PermissionEnum> permissions;
}
