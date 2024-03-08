/*
 * Author: Kevin Tamakuwala (21ITUBS120) 
 * Modified: 8th March 2024 10:45 PM
 * Purpose: This class is used to check if the user has permission to operate on a resource.
*/
package com.ddu.backend.utils;

import java.util.List;

import com.ddu.backend.entities.UserResourcePermission;
import com.ddu.backend.requests.ResourceReq;


public class HasAuthority {

    public static boolean hasPermissionToCreateResource(List<UserResourcePermission> permissions, ResourceReq input) {
        return permissions.stream()
                .anyMatch(permission -> permission.getResource().getResourceName().equals(input.getResourceName())
                        && permission.getResource().getResourceType().equals(input.getResourceType())
                        && permission.isCanWrite());
    }
}
