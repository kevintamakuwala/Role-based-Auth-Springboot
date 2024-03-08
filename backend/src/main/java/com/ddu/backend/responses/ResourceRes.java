/*
 * Author: Kevin Tamakuwala (21ITUBS120)
 * Modified: 8th March 2024 9:42 PM
 * Purpose: Resource User Response
*/
package com.ddu.backend.responses;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class ResourceRes {

    private Long id;
    private String resourceName;
    private String resourceType;
    private String parentResourceName;

}
