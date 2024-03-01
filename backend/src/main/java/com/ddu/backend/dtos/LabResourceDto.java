/*
 * Author: Kevin Tamakuwala (21ITUBS120)
 * Modified: 1st March 2024 2:27 PM
 * Purpose: This class is used to transfer the lab resource data.
*/

package com.ddu.backend.dtos;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class LabResourceDto {
    
    private String resourceName;
    private Long quantity;
    private Long labId;

    // Setters for chaining purpose
    public LabResourceDto setResourceName(String resourceName) {
        this.resourceName = resourceName;
        return this;
    }

    public LabResourceDto setQuantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }

    public LabResourceDto setLabId(Long labId) {
        this.labId = labId;
        return this;
    }
}
