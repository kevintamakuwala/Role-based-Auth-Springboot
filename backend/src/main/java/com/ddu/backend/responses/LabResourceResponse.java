/*
 * Author: Kevin Tamakuwala (21ITUBS120)
 * Modified: 1st March 2024 2:32 PM
 * Purpose: This class is used to represent the lab resource response.
*/

package com.ddu.backend.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LabResourceResponse {

    private Long resourceId;
    private String resourceName;
    private Long quantity;

    public LabResourceResponse setResourceId(Long resourceId) {
        this.resourceId = resourceId;
        return this;
    }

    public LabResourceResponse setResourceName(String resourceName) {
        this.resourceName = resourceName;
        return this;
    }

    public LabResourceResponse setQuantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }
}
