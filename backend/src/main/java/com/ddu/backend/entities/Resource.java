/*
 * Author: Kevin Tamakuwala (21ITUBS120)
 * Modified: 8th March 2024 9:41 PM
 * Purpose: This class uses Composite Design Pattern to handle the resource management.
*/

package com.ddu.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "resources")
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resource_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "resource_type")
    private ResourceEnum resourceType;

    @Column(name = "resource_name")
    private String resourceName;

    // Cascade Type.ALL ensures operations on the parent are cascaded to
    // subordinates
    @OneToMany(mappedBy = "parentResource", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Resource> subordinates = new ArrayList<>();

    @OneToMany(mappedBy = "resource", cascade = CascadeType.ALL)
    private List<UserResourcePermission> resourcePermissions = new ArrayList<>();

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Resource parentResource;

    public void addSubordinate(Resource resource) {
        resource.setParentResource(this);
        subordinates.add(resource);
    }

    public void removeSubordinate(Resource resource) {
        resource.setParentResource(null);
        this.subordinates.remove(resource);
    }

    // Setter for the entire list of subordinates
    public void setSubordinates(List<Resource> subordinates) {
        this.subordinates.clear();
        if (subordinates != null) {
            this.subordinates.addAll(subordinates);
            for (Resource subordinate : subordinates) {
                subordinate.setParentResource(this);
            }
        }
    }
}
