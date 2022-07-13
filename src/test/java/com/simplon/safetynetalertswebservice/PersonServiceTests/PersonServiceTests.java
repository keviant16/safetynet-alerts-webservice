package com.simplon.safetynetalertswebservice.PersonServiceTests;

import com.simplon.safetynetalertswebservice.model.entity.Person;
import com.simplon.safetynetalertswebservice.model.request.FullNameRequest;
import com.simplon.safetynetalertswebservice.repository.PersonRepository;
import com.simplon.safetynetalertswebservice.service.PersonService;
import com.simplon.safetynetalertswebservice.service.impl.IPersonService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PersonServiceTests {

    @Mock
    private PersonService personService;

    @InjectMocks
    private IPersonService iPersonService;
    @Mock
    private PersonRepository personRepository;

    @Test
    public void itShouldCreatePerson() {
        Person p = new Person();
        p.setFirstName("Bamab");
        p.setLastName("Ahmadu");
        p.setEmail("bamba@mail.com");

        when(personRepository.save(p)).thenReturn(p);
        Person savedPerson = iPersonService.createPerson(p);

        assertEquals(savedPerson.getFirstName(), p.getFirstName());
    }


    @Test
    public void itShouldReadPerson() {
        Person person = new Person();
        Person savePerson = personService.createPerson(person);
        Person findPerson = personService.readPerson(savePerson.getId());

        assertThat(findPerson).isNotNull();
    }

    @Test
    public void itShouldReadPersons() {
        Person person = new Person();
        Person person1 = new Person();
        Person person2 = new Person();

        personService.createPerson(person);
        personService.createPerson(person1);
        personService.createPerson(person2);

        List<Person> personList = (List<Person>) personService.readPersons();

        assertThat(personList.size()).isGreaterThan(0);
    }

    @Test
    public void itShouldReadPersonByFullName() {
        Person person = new Person();
        person.setFirstName("Nescafe");
        person.setLastName("Dora");

        personService.createPerson(person);

        Person findPerson = personService.readPersonByFullName(person.getFirstName(), person.getLastName());

        assertThat(findPerson).isNotNull();
    }

    @Test
    public void itShouldUpdatePerson() {
        Person person = new Person();
        Person savePerson = personService.createPerson(person);
        Person findPerson = personService.readPerson(savePerson.getId());

        findPerson.setPhone("0123456789");

        Person updatePerson = personService.createPerson(findPerson);

        assertThat(updatePerson.getPhone()).isEqualTo("0123456789");
    }

    ;

    @Test
    public void itShouldDeletePerson() {
        Person person = new Person();
        FullNameRequest fullNameRequest = new FullNameRequest("Jon", "Smith");
        person.setFirstName("Jon");
        person.setLastName("Smith");

        personService.createPerson(person);

        int deletePerson = personService.deletePerson(fullNameRequest);

        assertThat(deletePerson).isEqualTo(1);
    }
}
