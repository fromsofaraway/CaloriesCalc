package com.brow.caloriescalc.repository;

import com.brow.caloriescalc.model.Role;
import com.brow.caloriescalc.model.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> getRoleByName(RoleEnum role);
}
