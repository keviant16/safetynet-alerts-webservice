package com.simplon.safetynetalertswebservice.service.impl;

import com.simplon.safetynetalertswebservice.exception.ResourceNotFoundException;
import com.simplon.safetynetalertswebservice.model.entity.MedicalRecord;
import com.simplon.safetynetalertswebservice.model.entity.Person;
import com.simplon.safetynetalertswebservice.model.request.FullNameRequest;
import com.simplon.safetynetalertswebservice.repository.MedicalRecordRepository;
import com.simplon.safetynetalertswebservice.service.MedicalRecordService;
import com.simplon.safetynetalertswebservice.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IMedicalRecord implements MedicalRecordService {

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Autowired
    private PersonService personService;


    @Override
    public MedicalRecord createMedicalRecord(Long personId, MedicalRecord medicalRecord) {
        Person person = personService.readPerson(personId);
        medicalRecord.setPerson(person);
        return medicalRecordRepository.save(medicalRecord);
    }

    @Override
    public MedicalRecord readMedicalRecord(Long id) {
        return medicalRecordRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Medical Record not found"));
    }

    @Override
    public MedicalRecord updateMedicalRecord(Long id, MedicalRecord medicalRecord) {
        MedicalRecord findMedicalRecord = readMedicalRecord(id);
        findMedicalRecord.setBirthdate(medicalRecord.getBirthdate());
        findMedicalRecord.setMedications(medicalRecord.getMedications());
        findMedicalRecord.setAllergies(medicalRecord.getAllergies());

        return medicalRecordRepository.save(findMedicalRecord);
    }

    @Override
    public int deleteMedicalRecord(FullNameRequest fullNameRequest) {
        return medicalRecordRepository.deleteMedicalRecordByFirstNameAndLastName(fullNameRequest.getFirstName(), fullNameRequest.getLastName());
    }

    @Override
    public Iterable<MedicalRecord> readMedicalRecords() {
        return medicalRecordRepository.findAll();
    }
}
