package com.simplon.safetynetalertswebservice.service;

import com.simplon.safetynetalertswebservice.model.entity.Person;
import com.simplon.safetynetalertswebservice.model.request.FullNameRequest;

import java.util.List;

public interface PersonService {

    public Person createPerson(Person person);

    public Person readPerson(Long id);

    public Iterable<Person> readPersons();

    public Person readPersonByFullName(String firstName, String lastName);

    public List<Person> readPersonsByCity(String city);

    public Person updatePerson(Long id, Person person);

    public int deletePerson(FullNameRequest fullNameRequest);
}
