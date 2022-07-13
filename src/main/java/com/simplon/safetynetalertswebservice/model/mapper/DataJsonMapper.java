package com.simplon.safetynetalertswebservice.model.mapper;

import com.simplon.safetynetalertswebservice.model.entity.FireStation;
import com.simplon.safetynetalertswebservice.model.entity.MedicalRecord;
import com.simplon.safetynetalertswebservice.model.entity.Person;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DataJsonMapper {
    private List<Person> persons;
    private List<FireStationJsonMapper> firestations;
    private List<MedicalRecord> medicalrecords;
}
