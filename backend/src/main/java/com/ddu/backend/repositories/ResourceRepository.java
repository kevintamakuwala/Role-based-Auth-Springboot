/*
 * Author: Kevin Tamakuwala (21ITUBS120) 
 * Modified: 8th March 2024 10:45 PM
 * Purpose: ResourceRepository is used to manage the resources.
*/
package com.ddu.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ddu.backend.entities.Resource;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {
    public Optional<Resource> findByResourceName(String resourceName);
}
