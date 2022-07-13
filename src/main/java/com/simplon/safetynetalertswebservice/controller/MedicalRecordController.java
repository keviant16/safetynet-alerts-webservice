package com.simplon.safetynetalertswebservice.controller;

import com.simplon.safetynetalertswebservice.model.entity.MedicalRecord;
import com.simplon.safetynetalertswebservice.model.entity.Person;
import com.simplon.safetynetalertswebservice.model.request.FullNameRequest;
import com.simplon.safetynetalertswebservice.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@CrossOrigin(origins = "*")
public class MedicalRecordController {

   @Autowired
    private MedicalRecordService medicalRecordService;

    @PostMapping(value = "/medicalRecord")
    public ResponseEntity<MedicalRecord> createMedicalRecord(@RequestParam("personId") Long personId, @RequestBody MedicalRecord medicalRecord) {
        return new ResponseEntity<MedicalRecord>(medicalRecordService.createMedicalRecord(personId, medicalRecord), HttpStatus.CREATED);
    }

    @GetMapping(value = "/medicalRecord/{id}")
    public MedicalRecord readMedicalRecord(@PathVariable(value = "id") Long id) {
        return medicalRecordService.readMedicalRecord(id);
    }

    @GetMapping(value = "/medicalRecord")
    public Iterable<MedicalRecord> readMedicalRecords() {
        return medicalRecordService.readMedicalRecords();
    }

    @PutMapping(value = "/medicalRecord/{id}")
    public ResponseEntity<MedicalRecord> updateMedicalRecord(@PathVariable(value = "id") Long id, @RequestBody MedicalRecord medicalRecord) {
        return new ResponseEntity<MedicalRecord>(medicalRecordService.updateMedicalRecord(id, medicalRecord), HttpStatus.OK) ;
    }

    @DeleteMapping(value = "/medicalRecord")
    public ResponseEntity<String> deleteMedicalRecord(@RequestBody FullNameRequest fullNameRequest) {
        int isDeleted = medicalRecordService.deleteMedicalRecord(fullNameRequest);
        if(isDeleted == 0 ){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("MedicalRecord "+ fullNameRequest.getFirstName() + " " + fullNameRequest.getLastName() + " not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body("MedicalRecord "+ fullNameRequest.getFirstName() + " " + fullNameRequest.getLastName() + " has been deleted");
    }

}
