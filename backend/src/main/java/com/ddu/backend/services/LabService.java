/*
 * Author: Kevin Tamakuwala (21ITUBS120) 
 * Modified: 29th February 2024 5:05 PM
 * Purpose: Service for Lab
 */

package com.ddu.backend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddu.backend.dtos.LabDto;
import com.ddu.backend.entities.Lab;
import com.ddu.backend.entities.User;
import com.ddu.backend.repositories.LabRepository;
import com.ddu.backend.repositories.UserRepository;
import com.ddu.backend.responses.LabResourceResponse;
import com.ddu.backend.responses.LabResponse;
import com.ddu.backend.responses.UserResponse;

@Service
public class LabService {

    @Autowired
    LabRepository labRepository;

    @Autowired
    UserRepository userRepository;

    public Lab createLab(LabDto input) {
        if (input == null) {
            return null;
        }
        Optional<Lab> optionalLab = labRepository.findByName(input.getName());
        if (optionalLab.isPresent()) {
            return null;
        }
        Lab lab = new Lab().setName(input.getName()).setFaculties(new ArrayList<>()).setResources(new ArrayList<>());
        labRepository.save(lab);
        return lab;
    }

    public void assignFaculty(Long labId, Long facultyId) {

        Lab lab = labRepository.findById(labId).orElse(null);

        User faculty = userRepository.findById(facultyId).orElse(null);

        if (lab != null && faculty != null) {
            lab.getFaculties().add(faculty);
            faculty.setLab(lab);
            labRepository.save(lab);
            userRepository.save(faculty);
        } else {
            throw new RuntimeException("Lab or Faculty not found");
        }
    }

    public List<LabResponse> allLabs() {
        List<Lab> labs = labRepository.findAll();
        List<LabResponse> labResponses = new ArrayList<>();

        for (Lab lab : labs) {
            List<UserResponse> userResponses = lab.getFaculties().stream()
                    .map(user -> new UserResponse()
                            .setId(user.getId())
                            .setFullName(user.getFullName())
                            .setEmail(user.getEmail())
                            .setLabName(lab.getName())
                            .setRoleName(user.getRole().getName()))
                    .collect(Collectors.toList());

            List<LabResourceResponse> labResourceResponses = lab.getResources().stream()
                    .map(resource -> new LabResourceResponse()
                            .setResourceId(resource.getId())
                            .setResourceName(resource.getResourceName())
                            .setQuantity(resource.getQuantity()))
                    .collect(Collectors.toList());

            labResponses.add(
                    new LabResponse()
                            .setId(lab.getId())
                            .setName(lab.getName())
                            .setFaculties(userResponses)
                            .setResources(labResourceResponses));
        }
        return labResponses;
    }

    public LabResponse myLab(User user) {
        if (user != null) {
            if (user.getLab() == null) {
                throw new RuntimeException("Lab not found");
            }
            Lab lab = labRepository.findByName(user.getLab().getName()).orElse(null);

            if (lab != null) {
                List<UserResponse> userResponses = lab.getFaculties().stream().map(faculty -> new UserResponse()
                        .setId(faculty.getId())
                        .setFullName(faculty.getFullName())
                        .setEmail(faculty.getEmail())
                        .setLabName(lab.getName())
                        .setRoleName(faculty.getRole().getName())).collect(Collectors.toList());

                List<LabResourceResponse> labResponses = lab.getResources().stream()
                        .map(resource -> new LabResourceResponse()
                                .setResourceId(resource.getId())
                                .setResourceName(resource.getResourceName())
                                .setQuantity(resource.getQuantity()))
                        .collect(Collectors.toList());

                LabResponse labResponse = new LabResponse()
                        .setId(lab.getId())
                        .setName(lab.getName())
                        .setFaculties(userResponses)
                        .setResources(labResponses);

                return labResponse;
            } else {
                throw new RuntimeException("Lab not found");
            }
        } else {
            throw new RuntimeException("User not found");
        }
    }

    public List<LabResponse> otherLabs(User currentUser) {
        List<LabResponse> allLabs = allLabs();
        AtomicReference<LabResponse> myLab = new AtomicReference<>();

        try {
            myLab.set(myLab(currentUser));
        } catch (RuntimeException e) {
            if (!e.getMessage().equals("Lab not found")) {
                throw e;
            }
        }

        if (myLab.get() != null) {
            allLabs = allLabs.stream()
                    .filter(lab -> !lab.getLabId().equals(myLab.get().getLabId()))
                    .collect(Collectors.toList());
        }

        return allLabs;
    }
}
