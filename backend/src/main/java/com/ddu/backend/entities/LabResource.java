/*
 * Author: Kevin Tamakuwala (21ITUBS120)
 * Modified: 1st March 2024 2:12 PM
 * Purpose: This class is used to define the lab resource entity
*/
package com.ddu.backend.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lab_resources")
public class LabResource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "resource_name", unique = false, length = 100)
    private String resourceName;

    @Column(nullable = false, name = "quantity")
    private Long quantity;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "lab_id")
    private Lab lab;
    

    // Setters for chaining purpose
    public LabResource setId(Long id) {
        this.id = id;
        return this;
    }

    public LabResource setResourceName(String resourceName) {
        this.resourceName = resourceName;
        return this;
    }

    public LabResource setQuantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }

    public LabResource setLab(Lab lab) {
        this.lab = lab;
        return this;
    }
}
