/*
 * Author: Kevin Tamakuwala (21ITUBS120)
 * Modified: 8th March 2024 9:42 PM
 * Purpose: Resource User Request
*/
package com.ddu.backend.requests;

import com.ddu.backend.entities.ResourceEnum;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class ResourceReq {
    public String resourceName;
    @Enumerated(EnumType.STRING)
    public ResourceEnum resourceType;
    public String parentResourceName;    
}
