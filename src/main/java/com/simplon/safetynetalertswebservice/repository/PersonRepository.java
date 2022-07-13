package com.simplon.safetynetalertswebservice.repository;

import com.simplon.safetynetalertswebservice.model.entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface PersonRepository extends CrudRepository<Person, Long> {

    List<Person> findByCity(String city);
    Optional<Person> findPersonByFirstNameAndLastName(String firstName, String lastName);
    int deletePersonByFirstNameAndLastName(String firstName, String lastName);
}
