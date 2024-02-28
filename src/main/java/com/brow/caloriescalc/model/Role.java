package com.brow.caloriescalc.model;

import jakarta.persistence.*;

/**
 * Represents a role that can be assigned to a user, such as "user" or "admin".
 */
@Entity(name = "roles")
public class Role {
    @Id
    @Column
    private Long id;
    @Column(columnDefinition = "VARCHAR")
    @Enumerated(EnumType.STRING)
    private RoleEnum name;

    public Role(RoleEnum name) {
        this.name = name;
    }

    public Role() {}

    public RoleEnum getName() {
        return name;
    }

    public void setName(RoleEnum name) {
        this.name = name;
    }
}



