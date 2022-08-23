package com.simplon.safetynetalertswebservice.service.impl;

import com.simplon.safetynetalertswebservice.model.entity.ERole;
import com.simplon.safetynetalertswebservice.model.entity.Role;
import com.simplon.safetynetalertswebservice.repository.RoleRepository;
import com.simplon.safetynetalertswebservice.service.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IRoleService implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public Role findRoleByName(String name) {
        return roleRepository
                .findByName(ERole.valueOf(name))
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
    }

    @Override
    public void save(ERole eRole) {
        roleRepository.save(new Role(eRole));
    }
}
