package com.brow.caloriescalc.model;

import jakarta.persistence.*;

@Entity(name = "roles")
public class Role {
    @Id
    @Column
    private Long id;
    @Column(columnDefinition = "VARCHAR") // the only thing helped escape validation fail
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



