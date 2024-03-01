/*
 * Author: Kevin Tamakuwala (21ITUBS120) 
 * Modified: 29th February 2024 5:05 PM
 * Purpose: This class is used to represent the lab response.
 */
package com.ddu.backend.responses;

import java.util.List;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class LabResponse {
    private Long labId;
    private String name;
    private List<UserResponse> faculties;
    private List<LabResourceResponse> resources;

    public LabResponse setId(Long labId) {
        this.labId = labId;
        return this;
    }

    public LabResponse setName(String name) {
        this.name = name;
        return this;
    }

    public LabResponse setFaculties(List<UserResponse> faculties) {
        this.faculties = faculties;
        return this;
    }

    public LabResponse setResources(List<LabResourceResponse> resources) {
        this.resources = resources;
        return this;
    }

}
