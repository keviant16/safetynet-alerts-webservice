package com.simplon.safetynetalertswebservice.service.impl;


import com.simplon.safetynetalertswebservice.model.entity.ERole;
import com.simplon.safetynetalertswebservice.model.entity.Role;
import com.simplon.safetynetalertswebservice.model.entity.User;

import com.simplon.safetynetalertswebservice.model.request.LoginRequest;
import com.simplon.safetynetalertswebservice.model.request.SignupRequest;

import com.simplon.safetynetalertswebservice.model.response.AuthResponse;
import com.simplon.safetynetalertswebservice.model.response.MessageResponse;

import com.simplon.safetynetalertswebservice.repository.UserRepository;

import com.simplon.safetynetalertswebservice.security.jwt.JwtUtils;

import com.simplon.safetynetalertswebservice.security.service.UserDetailsImpl;
import com.simplon.safetynetalertswebservice.service.RoleService;
import com.simplon.safetynetalertswebservice.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseCookie;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class IUserService implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private RoleService roleService;

    @Override
    public AuthResponse authenticateUser(LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return new AuthResponse(userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), jwtCookie,roles);
    }

    @Override
    public MessageResponse registerUser(SignupRequest signUpRequest) {

        System.out.println("signup request roles :"+ signUpRequest.getRoles());

        if (existsByUsername(signUpRequest.getUsername()))
            return new MessageResponse("Error: Username is already taken!");

        if (existsByEmail(signUpRequest.getEmail()))
            return new MessageResponse("Error: Email is already taken!");

        User user = new User(
                signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        strRoles.forEach(role -> {
            Stream.of(ERole.values())
                    .filter(eRole -> eRole.toString().equals(role))
                    .forEach(eRole -> {
                        Role userRole = roleService.findRoleByName(eRole.toString());
                        roles.add(userRole);
                    });
        });

        user.setRoles(roles);
        save(user);
        return new MessageResponse("User saved !");
    }

    @Override
    public String logoutUser(){
        return jwtUtils.getCleanJwtCookie().toString();
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public void save(User user) {
          userRepository.save(user);
    }

    public User findByUsername(String username){
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
    }
}
