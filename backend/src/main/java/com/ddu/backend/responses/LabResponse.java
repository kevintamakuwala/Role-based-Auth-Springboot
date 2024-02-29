/*
 * Author: Kevin Tamakuwala (21ITUBS120) 
 * Modified: 29th February 2024 5:05 PM
 * Purpose: LabResponse is used to transfer the Lab data
 */
package com.ddu.backend.responses;

import java.util.List;


import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class LabResponse {
    private Long id;
    private String name;
    private List<UserResponse> faculties;

    public LabResponse setId(Long id) {
        this.id = id;
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

}
