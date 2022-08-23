package com.simplon.safetynetalertswebservice.controller;


import com.simplon.safetynetalertswebservice.model.entity.FireStation;
import com.simplon.safetynetalertswebservice.model.entity.Person;
import com.simplon.safetynetalertswebservice.model.request.FullNameRequest;
import com.simplon.safetynetalertswebservice.service.FireStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@CrossOrigin(origins = "*")
public class FireStationController {

    @Autowired
    private  FireStationService fireStationService;

    @PostMapping(value = "/fireStation")
    @PreAuthorize("hasRole('ROLE_STATION')")
    public ResponseEntity<String> createFireStation(@RequestBody FireStation fireStation) {
        FireStation savedFireStation = fireStationService.createFireStation(fireStation);
        return ResponseEntity.status(HttpStatus.CREATED).body("FireStation number "+ savedFireStation.getStation() + " has been created");
    }

    @GetMapping(value = "/fireStation/{id}")
    @PreAuthorize("hasRole('ROLE_STATION')")
    public FireStation readFireStation(@PathVariable(value = "id") Long id) {
        return fireStationService.readFireStation(id);
    }

    @GetMapping(value = "/fireStation")
    @PreAuthorize("hasRole('ROLE_STATION')")
    public Iterable<FireStation> readFireStations() {
        return fireStationService.readFireStations();
    }

    @PutMapping(value = "/fireStation/{id}")
    @PreAuthorize("hasRole('ROLE_STATION')")
    public FireStation updateFireStation(@PathVariable(value = "id") Long id, @RequestBody FireStation fireStation) {
        return fireStationService.updateFireStation(id, fireStation);
    }

    @DeleteMapping(value = "/fireStation/{id}")
    @PreAuthorize("hasRole('ROLE_STATION')")
    public ResponseEntity<String> deleteFireStation(@PathVariable(value = "id") Long id) {
        fireStationService.deleteFireStation(id);
        return ResponseEntity.status(HttpStatus.OK).body("FireStation has been delete");
    }
}
