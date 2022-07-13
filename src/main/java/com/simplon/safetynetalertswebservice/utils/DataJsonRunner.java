package com.simplon.safetynetalertswebservice.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.simplon.safetynetalertswebservice.model.entity.FireStation;
import com.simplon.safetynetalertswebservice.model.entity.MedicalRecord;
import com.simplon.safetynetalertswebservice.model.entity.Person;
import com.simplon.safetynetalertswebservice.model.mapper.DataJsonMapper;
import com.simplon.safetynetalertswebservice.model.mapper.FireStationJsonMapper;
import com.simplon.safetynetalertswebservice.repository.PersonRepository;
import com.simplon.safetynetalertswebservice.service.FireStationService;
import com.simplon.safetynetalertswebservice.service.MedicalRecordService;
import com.simplon.safetynetalertswebservice.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataJsonRunner implements CommandLineRunner {

    @Autowired
    private PersonService personService;

    @Autowired
    private FireStationService fireStationService;

    @Autowired
    private MedicalRecordService medicalRecordService;

    @Override
    public void run(String... args) throws Exception {
        try {
            ObjectMapper mapper = new ObjectMapper();
            DataJsonMapper dataJsonMapper = mapper.readValue(Paths.get("src/main/resources/data.json").toFile(), DataJsonMapper.class);
            List<Person> people = savePersons(dataJsonMapper.getPersons());
            saveFireStations(dataJsonMapper.getFirestations());
            saveMedicalRecords(people, dataJsonMapper.getMedicalrecords());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void saveMedicalRecords(List<Person> people, List<MedicalRecord> medicalRecords) {

    medicalRecords.forEach(medicalRecord -> {
        String medFullName = medicalRecord.getFirstName() + medicalRecord.getLastName();
        people.forEach(person -> {
            String perFullName = person.getFirstName() + person.getLastName();
            if (medFullName.equals(perFullName)) {
                System.out.println(medicalRecord);
               medicalRecordService.createMedicalRecord(person.getId(), medicalRecord);
            }
        });
    });
    }

    public List<Person> savePersons(List<Person> people){
        List<Person> savePersons = new ArrayList<>();
        people.forEach(person -> savePersons.add(personService.createPerson(person)));
        return savePersons;
    }

    public void saveFireStations(List<FireStationJsonMapper> fireStationJsonMappers){

        for(int i = 1; i <= 4; i++) {
            FireStation fireStation = new FireStation();
            fireStation.setStation(i);
            fireStation.setAddresses(new ArrayList<>());

            fireStationJsonMappers.forEach(fsm -> {
                if (fsm.getStation() == fireStation.getStation()) {
                    fireStation.getAddresses().add(fsm.getAddress());
                }
            });
            fireStationService.createFireStation(fireStation);
        }
    }

}
