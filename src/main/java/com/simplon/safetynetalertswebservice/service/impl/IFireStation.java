package com.simplon.safetynetalertswebservice.service.impl;

import com.simplon.safetynetalertswebservice.exception.ResourceNotFoundException;
import com.simplon.safetynetalertswebservice.model.entity.FireStation;
import com.simplon.safetynetalertswebservice.repository.FireStationRepository;
import com.simplon.safetynetalertswebservice.service.FireStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IFireStation implements FireStationService {

    @Autowired
    private FireStationRepository fireStationRepository;
    @Override
    public FireStation createFireStation(FireStation fireStation) {
        return fireStationRepository.save(fireStation);
    }

    @Override
    public FireStation readFireStation(Long id) {
        return fireStationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("FireStation not found"));
    }

    @Override
    public FireStation updateFireStation(Long id, FireStation fireStation) {
        FireStation findFireStation = readFireStation(id);
        findFireStation.setStation(fireStation.getStation());
        findFireStation.setAddresses(fireStation.getAddresses());

        return fireStationRepository.save(findFireStation);
    }

    @Override
    public void deleteFireStation(Long id) {
        fireStationRepository.deleteById(id);
    }

    @Override
    public Iterable<FireStation> readFireStations() {
        return fireStationRepository.findAll();
    }

    @Override
    public FireStation readFireStationByStation(int stationNumber) {
        return fireStationRepository.findByStation(stationNumber).orElseThrow(() -> new ResourceNotFoundException("FireStation not found"));
    }
}
