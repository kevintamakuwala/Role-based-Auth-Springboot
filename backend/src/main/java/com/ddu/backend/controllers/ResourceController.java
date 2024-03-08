/*
 * Author: Kevin Tamakuwala (21ITUBS120)
 * Modified: 8th March 2024 9:38 PM
 *  Purpose: This class is used to handle the resource related operations.
*/
package com.ddu.backend.controllers;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ddu.backend.entities.RoleEnum;
import com.ddu.backend.entities.User;
import com.ddu.backend.entities.UserResourcePermission;
import com.ddu.backend.requests.PermissionReq;
import com.ddu.backend.requests.ResourceReq;
import com.ddu.backend.responses.ResourceRes;
import com.ddu.backend.services.ResourceService;
import com.ddu.backend.utils.HasAuthority;

@RequestMapping("/resource")
@RestController
public class ResourceController {
    // @Autowired
    // private UserRepository userRepository;
    @Autowired
    private ResourceService resourceService;

    @PreAuthorize("hasRole('HOD') or hasRole('FACULTY')")
    @PostMapping
    public ResponseEntity<ResourceRes> createResource(@RequestBody ResourceReq input) {

        // getting user Object from the security context.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();

        if (currentUser.getRole().getRoleType().equals(RoleEnum.HOD)) {
            return ResponseEntity.ok(resourceService.createResource(input));
        }

        // getting the list of permissions from the user object.
        List<UserResourcePermission> permissions = Optional.ofNullable(currentUser.getUserPermissions())
                .orElse(Collections.emptyList());

        // check if the user has permission to create the resource.
        boolean hasPermission = HasAuthority.hasPermissionToCreateResource(permissions, input);

        if (!hasPermission) {
            throw new RuntimeException("You don't have permission to create this resource");
        }

        return ResponseEntity.ok(resourceService.createResource(input));
    }

    @PreAuthorize("hasRole('HOD')")
    @PostMapping("/assign-permissions")
    public ResponseEntity<Void> assignPermissions(@RequestBody PermissionReq input) {
        resourceService.assignPermissions(input);
        return ResponseEntity.ok().build();
    }
}
