/*
 * Author: Kevin Tamakuwala (21ITUBS120) 
 * Modified: 8th March 2024 10:45 PM
 * Purpose: ResourceService is used to manage the resources and their permissions
 */
package com.ddu.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddu.backend.entities.PermissionEnum;
import com.ddu.backend.entities.Resource;
import com.ddu.backend.entities.User;
import com.ddu.backend.entities.UserResourcePermission;
import com.ddu.backend.repositories.ResourceRepository;
import com.ddu.backend.repositories.UserRepository;
import com.ddu.backend.repositories.UserResourcePermissionRepository;
import com.ddu.backend.requests.PermissionReq;
import com.ddu.backend.requests.ResourceReq;
import com.ddu.backend.responses.ResourceRes;

import jakarta.transaction.Transactional;

@Service
public class ResourceService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private UserResourcePermissionRepository userResourcePermissionRepository;

    public ResourceRes createResource(ResourceReq input) {
        Resource resource = new Resource();

        Resource parentResource = resourceRepository.findByResourceName(input.getParentResourceName())
                .orElse(null);

        resource.setParentResource(parentResource);
        resource.setResourceName(input.getResourceName());
        resource.setResourceType(input.getResourceType());
        resource = resourceRepository.save(resource);

        ResourceRes resourceRes = new ResourceRes();
        resourceRes.setId(resource.getId());
        resourceRes.setResourceName(resource.getResourceName());
        resourceRes.setResourceType(resource.getResourceType().toString());

        if (parentResource != null) {
            resourceRes.setParentResourceName(resource.getParentResource().getResourceName());
            parentResource.addSubordinate(resource);
            resourceRepository.save(parentResource);
        } else {
            resourceRes.setParentResourceName("");
        }
        return resourceRes;
    }

    @Transactional
    public void assignPermissions(PermissionReq input) {
        Resource resource = resourceRepository.findByResourceName(input.getResourceName())
                .orElseThrow(() -> new RuntimeException("Resource not found"));
        User user = userRepository.findById(input.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        List<PermissionEnum> permissions = input.getPermissions();

        UserResourcePermission userResourcePermission = userResourcePermissionRepository
                .findByUserAndResource(user, resource)
                .orElse(new UserResourcePermission());

        userResourcePermission.setCanRead(permissions.contains(PermissionEnum.READ));
        userResourcePermission.setCanWrite(permissions.contains(PermissionEnum.WRITE));
        userResourcePermission.setCanUpdate(permissions.contains(PermissionEnum.UPDATE));
        userResourcePermission.setCanDelete(permissions.contains(PermissionEnum.DELETE));

        userResourcePermission = userResourcePermissionRepository.save(userResourcePermission);

        resource.getResourcePermissions().add(userResourcePermission);
        user.getUserPermissions().add(userResourcePermission);

        resource = resourceRepository.save(resource);
        user = userRepository.save(user);

        userResourcePermission.setResource(resource);
        userResourcePermission.setUser(user);

        userResourcePermissionRepository.save(userResourcePermission);
    }
}
