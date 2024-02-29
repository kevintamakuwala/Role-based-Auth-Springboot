/*
 * Author: Kevin Tamakuwala (21ITUBS120) 
 * Modified: 29th February 2024 1:30 AM
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
import java.util.Map;
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
        RoleEnum[] roleNames = new RoleEnum[]{RoleEnum.FACULTY, RoleEnum.HOD};
        Map<RoleEnum, String> roleDescriptionMap = Map.of(RoleEnum.FACULTY, "FACULTY role", RoleEnum.HOD, "HOD role");

        Arrays.stream(roleNames).forEach(roleName -> {
            Optional<Role> optionalRole = roleRepository.findByName(roleName);

            if (optionalRole.isEmpty()) {
                Role roleToCreate = new Role()
                        .setName(roleName)
                        .setDescription(roleDescriptionMap.get(roleName));

                roleRepository.save(roleToCreate);
            }
        });

    }
}