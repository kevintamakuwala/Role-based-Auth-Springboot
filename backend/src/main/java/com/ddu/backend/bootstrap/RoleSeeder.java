/*
 * Author: Kevin Tamakuwala (21ITUBS120) 
 * Modified: 8th March 2024 9:37 PM
 * Purpose: This class is used to seed the database with roles 
 */
package com.ddu.backend.bootstrap;

import com.ddu.backend.entities.Role;
import com.ddu.backend.entities.RoleEnum;
import com.ddu.backend.repositories.RoleRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

@Component
public class RoleSeeder implements ApplicationListener<ContextRefreshedEvent> {
    private final RoleRepository roleRepository;

    public RoleSeeder(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        this.loadRoles();
    }

    private void loadRoles() {
        RoleEnum[] roleNames = new RoleEnum[] { RoleEnum.HOD, RoleEnum.FACULTY };

        Arrays.stream(roleNames).forEach(roleName -> {
            Optional<Role> optionalRole = roleRepository.findByRoleType(roleName);

            if (optionalRole.isEmpty()) {
                Role roleToCreate = new Role();

                roleToCreate.setRoleType(roleName);

                roleRepository.save(roleToCreate);
            }
        });

    }
}