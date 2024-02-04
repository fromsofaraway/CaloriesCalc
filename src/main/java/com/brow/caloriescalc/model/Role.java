package com.brow.caloriescalc.model;

import jakarta.persistence.*;

@Entity
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

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }
}



