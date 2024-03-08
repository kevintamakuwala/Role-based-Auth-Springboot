/*
 * Author: Kevin Tamakuwala (21ITUBS120) 
 * Modified: 8th March 2024 10:45 PM
 * Purpose: UserResourcePermissionRepository is used to manage the user's permission for a resource.
*/
package com.ddu.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ddu.backend.entities.Resource;
import com.ddu.backend.entities.User;
import com.ddu.backend.entities.UserResourcePermission;

@Repository
public interface UserResourcePermissionRepository extends JpaRepository<UserResourcePermission, Long> {

    public Optional<UserResourcePermission> findByUserAndResource(User user, Resource resource);
}
