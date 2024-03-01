/*
 * Author: Kevin Tamakuwala (21ITUBS120)
 * Modified: 1st March 2024 3:29 PM
 * Purpose: repository for lab resource. 
*/

package com.ddu.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ddu.backend.entities.LabResource;

@Repository
public interface LabResourceRepository extends JpaRepository<LabResource, Long> {

    // finding a lab resource by its name
    Optional<LabResource> findByResourceName(String resourceName);

    // composite key (labId, resourceName)
    Optional<LabResource> findByLabIdAndResourceName(Long labId, String resourceName);
}
