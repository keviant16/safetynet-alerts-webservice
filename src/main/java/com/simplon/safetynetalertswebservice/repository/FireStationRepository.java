package com.simplon.safetynetalertswebservice.repository;

import com.simplon.safetynetalertswebservice.model.entity.FireStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FireStationRepository extends JpaRepository<FireStation, Long> {
    @Override
    Optional<FireStation> findById(Long aLong);

    Optional<FireStation> findByStation(int stationNumber);

    //delete address mapping to fireStation
}
