/*
 * Author: Kevin Tamakuwala (21ITUBS120) 
 * Modified: 29th February 2024 5:05 PM
 * Purpose: LabDto is used to transfer the Lab data
 */
package com.ddu.backend.dtos;

import lombok.ToString;
import lombok.Getter;

@Getter
@ToString
public class LabDto {
    private String name;

    public LabDto setName(String name) {
        this.name = name;
        return this;
    }

}