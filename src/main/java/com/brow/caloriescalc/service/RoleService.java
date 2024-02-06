package com.brow.caloriescalc.service;

import com.brow.caloriescalc.model.Role;
import com.brow.caloriescalc.model.RoleEnum;
import com.brow.caloriescalc.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Optional<Role> getRoleByName(RoleEnum role) {
        return roleRepository.getRoleByName(role);
    }
}
