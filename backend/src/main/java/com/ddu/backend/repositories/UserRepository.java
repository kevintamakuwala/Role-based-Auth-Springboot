/*
 * Author: Kevin Tamakuwala (21ITUBS120) 
 * Modified: 1st March 2024 3:29 PM
 * Purpose: repository for user.
 */
package com.ddu.backend.repositories;

import com.ddu.backend.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    // finding a user by its email.
    Optional<User> findByEmail(String email);
}
