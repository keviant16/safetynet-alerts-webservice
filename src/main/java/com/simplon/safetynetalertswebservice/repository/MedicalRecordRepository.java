package com.simplon.safetynetalertswebservice.repository;

import com.simplon.safetynetalertswebservice.model.entity.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {

    //delete medical record by firstName and lastName
    int deleteMedicalRecordByFirstNameAndLastName(String firstName, String lastName);
}
