package com.simplon.safetynetalertswebservice.repository;

import com.simplon.safetynetalertswebservice.model.entity.ERole;
import com.simplon.safetynetalertswebservice.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(ERole name);
}
