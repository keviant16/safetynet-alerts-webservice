package com.simplon.safetynetalertswebservice.service;

import com.simplon.safetynetalertswebservice.model.entity.MedicalRecord;
import com.simplon.safetynetalertswebservice.model.request.FullNameRequest;

public interface MedicalRecordService {

    MedicalRecord createMedicalRecord(Long personId, MedicalRecord medicalRecord) ;

     MedicalRecord readMedicalRecord(Long id);

    MedicalRecord updateMedicalRecord(Long id, MedicalRecord medicalRecord);

     int deleteMedicalRecord(FullNameRequest fullNameRequest);

    Iterable<MedicalRecord> readMedicalRecords();
}
