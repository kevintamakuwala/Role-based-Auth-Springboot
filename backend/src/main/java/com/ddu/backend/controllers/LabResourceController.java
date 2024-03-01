/*
 * Author: Kevin Tamakuwala (21ITUBS120)
 * Modified: 1st March 2024 2:18 PM
 * Purpose: This class is used to handle the lab resource related operations.
*/

package com.ddu.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ddu.backend.dtos.LabResourceDto;
import com.ddu.backend.responses.LabResourceResponse;
import com.ddu.backend.services.LabResourceService;

@RequestMapping("/lab-resource")
@Controller
public class LabResourceController {
    @Autowired
    private LabResourceService labResourceService;

    // Only HOD can create a lab resource

    @PreAuthorize("hasRole('HOD')")
    @PostMapping
    public ResponseEntity<LabResourceResponse> createLabResource(@RequestBody LabResourceDto input) {
        LabResourceResponse labResource = labResourceService.createLabResource(input);
        return ResponseEntity.ok(labResource);
    }
}
