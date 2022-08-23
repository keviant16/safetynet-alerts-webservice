package com.simplon.safetynetalertswebservice.service;

import com.simplon.safetynetalertswebservice.model.entity.User;
import com.simplon.safetynetalertswebservice.model.request.LoginRequest;
import com.simplon.safetynetalertswebservice.model.request.SignupRequest;
import com.simplon.safetynetalertswebservice.model.response.AuthResponse;
import com.simplon.safetynetalertswebservice.model.response.MessageResponse;

public interface UserService {

    AuthResponse authenticateUser(LoginRequest loginRequest);

    MessageResponse registerUser(SignupRequest signUpRequest);

    String logoutUser();

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    void save(User user);

    User findByUsername(String username);
}
