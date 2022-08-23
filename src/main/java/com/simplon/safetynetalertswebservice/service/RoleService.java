package com.simplon.safetynetalertswebservice.service;

import com.simplon.safetynetalertswebservice.model.entity.ERole;
import com.simplon.safetynetalertswebservice.model.entity.Role;

public interface RoleService {
    Role findRoleByName(String name);
    void save(ERole eRole);
}
