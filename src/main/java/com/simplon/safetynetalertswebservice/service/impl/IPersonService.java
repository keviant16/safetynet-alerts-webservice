package com.simplon.safetynetalertswebservice.service.impl;

import com.simplon.safetynetalertswebservice.exception.ResourceNotFoundException;
import com.simplon.safetynetalertswebservice.model.entity.MedicalRecord;
import com.simplon.safetynetalertswebservice.model.entity.Person;
import com.simplon.safetynetalertswebservice.model.request.FullNameRequest;
import com.simplon.safetynetalertswebservice.repository.PersonRepository;
import com.simplon.safetynetalertswebservice.service.MedicalRecordService;
import com.simplon.safetynetalertswebservice.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IPersonService implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private MedicalRecordService medicalRecordService;

    @Override
    public Person createPerson(Person person) {
        //save medicalRecord
        return personRepository.save(person);
    }

    @Override
    public Person readPerson(Long id) {
        return personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Person not found"));
    }

    @Override
    public Iterable<Person> readPersons() {
        return personRepository.findAll();
    }

    @Override
    public Person readPersonByFullName(String firstName, String lastName) {
        return personRepository.findPersonByFirstNameAndLastName(firstName, lastName).orElseThrow(() -> new ResourceNotFoundException("Person not found"));
    }
    @Override
    public List<Person> readPersonsByCity(String city) {
        return personRepository.findByCity(city);
    }

    @Override
    public Person updatePerson(Long id, Person person) {
        Person findPerson = readPerson(id);
        findPerson.setZip(person.getZip());
        findPerson.setEmail(person.getEmail());
        findPerson.setAddress(person.getAddress());
        findPerson.setCity(person.getCity());
        findPerson.setPhone(person.getPhone());
        findPerson.setMedicalRecord(person.getMedicalRecord());

        return personRepository.save(findPerson);
    }

    @Override
    public int deletePerson(FullNameRequest fullNameRequest) {
        medicalRecordService.deleteMedicalRecord(fullNameRequest);
        return personRepository.deletePersonByFirstNameAndLastName(fullNameRequest.getFirstName(), fullNameRequest.getLastName());
    }

}
