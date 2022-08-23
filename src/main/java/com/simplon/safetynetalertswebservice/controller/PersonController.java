package com.simplon.safetynetalertswebservice.controller;

import com.simplon.safetynetalertswebservice.model.entity.Person;
import com.simplon.safetynetalertswebservice.model.request.FullNameRequest;
import com.simplon.safetynetalertswebservice.model.response.PersonInfo;
import com.simplon.safetynetalertswebservice.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@ResponseBody
@CrossOrigin(origins = "*")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping(value = "/person")
    @PreAuthorize("hasRole('ROLE_PERSON') ")
    public ResponseEntity<String> createPerson(@RequestBody Person person) {
        Person savedPerson = personService.createPerson(person);
        return ResponseEntity.status(HttpStatus.CREATED).body("Person "+ savedPerson.getFirstName() + " " + savedPerson.getLastName() + " has been created");
    }

    @GetMapping(value = "/person/{id}")
    @PreAuthorize("hasRole('ROLE_PERSON') ")
    public Person readPerson(@PathVariable(value = "id") Long id) {
        return personService.readPerson(id);
    }


    @GetMapping(value = "/person")
    @PreAuthorize("hasRole('ROLE_PERSON') ")
    public Iterable<Person> readPersons() {
        return personService.readPersons();
    }


    @PutMapping(value = "/person/{id}")
    @PreAuthorize("hasRole('ROLE_PERSON') ")
    public Person updatePerson(@PathVariable(value = "id") Long id, @RequestBody Person person) {
        return personService.updatePerson(id, person);
    }

    @DeleteMapping(value = "/person")
    @PreAuthorize("hasRole('ROLE_PERSON')")
    public ResponseEntity<String> deletePerson(@RequestBody FullNameRequest fullNameRequest) {
        int isDeleted = personService.deletePerson(fullNameRequest);
        if(isDeleted == 0 ){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person "+ fullNameRequest.getFirstName() + " " + fullNameRequest.getLastName() + " not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Person "+ fullNameRequest.getFirstName() + " " + fullNameRequest.getLastName() + " has been deleted");
    }


}
