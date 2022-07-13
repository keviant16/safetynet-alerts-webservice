package com.simplon.safetynetalertswebservice.PersonRepositoryTests;

import com.simplon.safetynetalertswebservice.model.entity.Person;
import com.simplon.safetynetalertswebservice.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PersonRepositoryTests {

    @Mock
    private PersonRepository personRepository;

    @Test
    public void itShouldSavePerson() {
        Person person = new Person();
        Person savedPerson = personRepository.save(person);
        assertThat(savedPerson).isNotNull();
    }

    @Test
    public void itShouldFindAllPersons() {
        Person person = new Person();
        Person person1 = new Person();
        Person person2 = new Person();

        personRepository.save(person);
        personRepository.save(person1);
        personRepository.save(person2);

        List<Person> personList = (List<Person>) personRepository.findAll();
        assertThat(personList.size()).isGreaterThan(0);
    }

    @Test
    public void itShouldFindPersonsByCity() {
        Person person = new Person();
        person.setCity("Nancy");

        personRepository.save(person);

        List<Person> people = personRepository.findByCity("Nancy");

        assertThat(people.size()).isGreaterThan(0);
    }

    @Test
    public void itShouldFindPersonByFirstNameAndLastName() {
        Person person = new Person();
        person.setFirstName("Nescafe");
        person.setLastName("Dora");

        personRepository.save(person);

        Person findPerson = personRepository.findPersonByFirstNameAndLastName(person.getFirstName(), person.getLastName()).get();

        assertThat(findPerson).isNotNull();
    }

    @Test
    public void isShouldFindPersonById() {
        Person person = new Person();
        Person savePerson = personRepository.save(person);
        Person findPerson = personRepository.findById(savePerson.getId()).get();

        assertThat(findPerson).isNotNull();
    }


    @Test
    public void itShouldUpdatePerson() {
        Person person = new Person();
        Person savePerson = personRepository.save(person);
        Person findPerson = personRepository.findById(savePerson.getId()).get();

        findPerson.setPhone("0123456789");

        Person updatePerson = personRepository.save(findPerson);

        assertThat(updatePerson.getPhone()).isEqualTo("0123456789");
    }

    @Test
    public void itShouldDeletePersonById() {
        Person person = new Person();
        Person savePerson = personRepository.save(person);

        personRepository.deleteById(savePerson.getId());

        boolean isEmpty = personRepository.findById(savePerson.getId()).isEmpty();

        assertThat(isEmpty).isTrue();
    }

    @Test
    public void itShouldDeletePersonByFirstNameAndLastName() {
        Person person = new Person();
        person.setFirstName("Jon");
        person.setLastName("Smith");
        Person savePerson = personRepository.save(person);

        int deletePerson = personRepository.deletePersonByFirstNameAndLastName(savePerson.getFirstName(), savePerson.getLastName());

        assertThat(deletePerson).isEqualTo(1);
    }
}
