package com.simplon.safetynetalertswebservice.service.impl;

import com.simplon.safetynetalertswebservice.model.entity.Person;
import com.simplon.safetynetalertswebservice.model.response.PersonInfo;
import com.simplon.safetynetalertswebservice.service.FireStationService;
import com.simplon.safetynetalertswebservice.service.MedicalRecordService;
import com.simplon.safetynetalertswebservice.service.PersonService;
import com.simplon.safetynetalertswebservice.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IUrl implements UrlService {

    @Autowired
    private PersonService personService;

    @Autowired
    private FireStationService fireStationService;

    @Autowired
    private MedicalRecordService medicalRecordService;

    @Override
    public List<String> readCommunityEmail(String city) {
        List<Person> personList = personService.readPersonsByCity(city);
        List<String> emails = new ArrayList<String>();
        for (int i = 0; i < personList.size(); i++) {
            emails.add(personList.get(i).getEmail());
        }
        return emails;
    }

    @Override
    public List<PersonInfo> readPersonInfo(String firstName, String lastName) {

        List<PersonInfo> personInfoList = new ArrayList<>();
        Iterable<Person> people = personService.readPersons();

        people.forEach(p -> {
            if (p.getLastName().equals(lastName) && p.getFirstName().equals(firstName)) {

                PersonInfo personInfo = new PersonInfo();
                personInfo.setFirstName(p.getFirstName());
                personInfo.setLastName(p.getLastName());
                personInfo.setEmail(p.getEmail());
                personInfo.setBirthdate(p.getMedicalRecord().getBirthdate());


                if (!(p.getMedicalRecord() == null)) {
                    personInfo.setAllergies(p.getMedicalRecord().getAllergies());
                    personInfo.setMedications(p.getMedicalRecord().getMedications());
                }
                personInfoList.add(personInfo);
            }
        });
        return personInfoList;
    }
}
