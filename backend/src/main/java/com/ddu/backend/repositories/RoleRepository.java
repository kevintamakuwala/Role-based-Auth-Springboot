/*
 * Author: Kevin Tamakuwala (21ITUBS120) 
 * Modified: 1st March 2024 3:29 PM
 * Purpose: repository for role.
 */
package com.ddu.backend.repositories;

import com.ddu.backend.entities.Role;
import com.ddu.backend.entities.RoleEnum;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    // Finding a role by its name
    Optional<Role> findByName(RoleEnum name);
}
