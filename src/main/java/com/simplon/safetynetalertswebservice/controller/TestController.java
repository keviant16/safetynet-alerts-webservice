package com.simplon.safetynetalertswebservice.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/people")
    @PreAuthorize("hasRole('ROLE_PERSON') ")
    public String peopleAccess() {
        return "people Content.";
    }

    @GetMapping("/station")
    @PreAuthorize("hasRole('ROLE_STATION') or hasRole('ROLE_PERSON')")
    public String stationAccess() {
        return "station Board.";
    }
}
