package com.brow.caloriescalc.model;

import jakarta.persistence.*;

@Entity(name = "roles")
public class Role {
    @Id
    @Column
    private Long id;
    @Column
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    public Role(RoleEnum role) {
        this.role = role;
    }

    public Role() {}

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }
}



