package com.simplon.safetynetalertswebservice.repository;

import com.simplon.safetynetalertswebservice.model.entity.FireStation;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface FireStationRepository extends CrudRepository<FireStation, Long> {
    @Override
    Optional<FireStation> findById(Long aLong);

    Optional<FireStation> findByStation(int stationNumber);

    //delete address mapping to fireStation
}
