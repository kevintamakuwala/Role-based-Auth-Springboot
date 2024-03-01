/*
 * Author: Kevin Tamakuwala (21ITUBS120) 
 * Modified: 1st March 2024 5:10 PM
 * Purpose: repository for lab.
 */
package com.ddu.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ddu.backend.entities.Lab;

@Repository
public interface LabRepository extends JpaRepository<Lab, Long> {

    // finding a lab by its name
    Optional<Lab> findByName(String labName);
}
