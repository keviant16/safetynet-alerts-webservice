package com.simplon.safetynetalertswebservice.service;

import com.simplon.safetynetalertswebservice.model.entity.FireStation;
import com.simplon.safetynetalertswebservice.model.entity.Person;
import com.simplon.safetynetalertswebservice.repository.FireStationRepository;
import com.simplon.safetynetalertswebservice.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface FireStationService {

    FireStation createFireStation(FireStation fireStation);

    FireStation readFireStation(Long id);

    FireStation updateFireStation(Long id, FireStation fireStation);

    void deleteFireStation(Long id);

    Iterable<FireStation> readFireStations();

    FireStation readFireStationByStation(int stationNumber);
}
