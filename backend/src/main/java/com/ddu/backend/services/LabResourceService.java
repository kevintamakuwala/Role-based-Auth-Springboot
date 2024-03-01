/*
 * Author: Kevin Tamakuwala (21ITUBS120)
 * Modified: 1st March 2024 5:07 PM
 * Purpose: Service for Lab Resource
*/

package com.ddu.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddu.backend.dtos.LabResourceDto;
import com.ddu.backend.entities.Lab;
import com.ddu.backend.entities.LabResource;
import com.ddu.backend.repositories.LabRepository;

import com.ddu.backend.repositories.LabResourceRepository;
import com.ddu.backend.responses.LabResourceResponse;

@Service
public class LabResourceService {

    @Autowired
    private LabResourceRepository labResourceRepository;
    @Autowired
    private LabRepository labRepository;

    public LabResourceResponse createLabResource(LabResourceDto input) {

        Lab lab = labRepository.findById(input.getLabId()).orElse(null);

        if (lab == null) {
            throw new RuntimeException("Lab not found");
        }

        // Check if the lab already has the resource.
        LabResource labResource = labResourceRepository
                .findByLabIdAndResourceName(input.getLabId(), input.getResourceName()).orElse(null);

        // If the lab already has the resource, then we need to update the quantity.
        if (labResource != null) {
            labResource.setQuantity(labResource.getQuantity() + input.getQuantity());
            labResource = labResourceRepository.save(labResource);
        }
        // No Composite key found. So, we need to create a new lab resource.
        else {
            labResource = labResourceRepository
                    .save(new LabResource()
                            .setQuantity(input.getQuantity())
                            .setResourceName(input.getResourceName())
                            .setLab(lab));
        }

        lab.getResources().add(labResource);
        labRepository.save(lab);

        return new LabResourceResponse(
                labResource.getId(),
                labResource.getResourceName(),
                labResource.getQuantity());
    }

}
