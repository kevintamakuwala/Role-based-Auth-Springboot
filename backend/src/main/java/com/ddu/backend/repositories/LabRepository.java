/*
 * Author: Kevin Tamakuwala (21ITUBS120) 
 * Modified: 29th February 2024 5:05 PM
 * Purpose: repository for lab
 */
package com.ddu.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ddu.backend.entities.Lab;

@Repository
public interface LabRepository extends JpaRepository<Lab, Long> {

    Optional<Lab> findByName(String labName);
}
