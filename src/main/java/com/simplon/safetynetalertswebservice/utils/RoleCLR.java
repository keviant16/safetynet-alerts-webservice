package com.simplon.safetynetalertswebservice.utils;

import com.simplon.safetynetalertswebservice.model.entity.ERole;
import com.simplon.safetynetalertswebservice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import org.springframework.stereotype.Component;

@Component
public class RoleCLR implements CommandLineRunner {

    @Autowired
    private RoleService roleService;

    @Override
    public void run(String... args) throws Exception {
        roleService.save(ERole.ROLE_PERSON);
        roleService.save(ERole.ROLE_STATION);
    }
}
