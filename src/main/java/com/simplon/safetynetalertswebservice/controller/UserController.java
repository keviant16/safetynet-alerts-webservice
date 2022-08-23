package com.simplon.safetynetalertswebservice.controller;

import com.simplon.safetynetalertswebservice.model.request.LoginRequest;
import com.simplon.safetynetalertswebservice.model.request.SignupRequest;
import com.simplon.safetynetalertswebservice.model.response.AuthResponse;
import com.simplon.safetynetalertswebservice.model.response.MessageResponse;
import com.simplon.safetynetalertswebservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        AuthResponse authResponse = userService.authenticateUser(loginRequest);
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, authResponse.getJwtCookie().toString()).body(authResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

        MessageResponse messageResponse = userService.registerUser(signUpRequest);
        return ResponseEntity.ok(messageResponse);
    }

    @PostMapping("/signout")
    @PreAuthorize("hasRole('ROLE_STATION') or hasRole('ROLE_PERSON')")
    public ResponseEntity<?> logoutUser() {
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, userService.logoutUser())
                .body(new MessageResponse("You've been signed out!"));
    }

}
