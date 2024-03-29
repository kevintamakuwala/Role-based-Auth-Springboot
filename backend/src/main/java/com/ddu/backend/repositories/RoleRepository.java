/*
 * Author: Kevin Tamakuwala (21ITUBS120) 
 * Modified: 8th March 2024 10:45 PM
 * Purpose: RoleRepository is used to manage the roles.
*/
package com.ddu.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ddu.backend.entities.Role;
import com.ddu.backend.entities.RoleEnum;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    public Optional<Role> findByRoleType(RoleEnum roleEnum);
}
