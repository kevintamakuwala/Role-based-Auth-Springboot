/*
 * Author: Kevin Tamakuwala (21ITUBS120) 
 * Modified: 29th February 2024 5:05 PM
 * Purpose: This class is used to handle the lab related operations
 */
package com.ddu.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ddu.backend.dtos.LabDto;
import com.ddu.backend.entities.Lab;
import com.ddu.backend.entities.User;
import com.ddu.backend.responses.LabResponse;
import com.ddu.backend.services.LabService;

@Controller
@RequestMapping("/labs")
public class LabController {

    @Autowired
    private LabService labService;

    // Only HOD can create a lab
    @PreAuthorize("hasRole('HOD')")
    @PostMapping
    public ResponseEntity<Lab> createLab(@RequestBody LabDto input) {
        Lab lab = labService.createLab(input);
        return ResponseEntity.ok(lab);
    }

    // Only HOD can assign a faculty to a lab
    @PreAuthorize("hasRole('HOD')")
    @PostMapping("/assign-faculty/{labId}/{facultyId}")
    public ResponseEntity<Void> assignFaculty(@PathVariable Long labId, @PathVariable Long facultyId) {
        labService.assignFaculty(labId, facultyId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('FACULTY', 'HOD')")
    public ResponseEntity<List<LabResponse>> allLabs() {
        return ResponseEntity.ok(labService.allLabs());
    }

    // endpoint to get my lab
    @GetMapping("/mylab")
    @PreAuthorize("hasAnyRole('FACULTY', 'HOD')")
    public ResponseEntity<LabResponse> myLab() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();

        LabResponse labResponse = labService.myLab(currentUser);

        return ResponseEntity.ok(labResponse);
    }

    // endpoint to get other labs
    @GetMapping("/other-labs")
    @PreAuthorize("hasAnyRole('FACULTY', 'HOD')")
    public ResponseEntity<List<LabResponse>> otherLabs() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();

        return ResponseEntity.ok(labService.otherLabs(currentUser));
    }

}
