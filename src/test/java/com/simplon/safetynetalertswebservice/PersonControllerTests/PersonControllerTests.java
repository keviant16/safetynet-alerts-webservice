package com.simplon.safetynetalertswebservice.PersonControllerTests;

import com.simplon.safetynetalertswebservice.model.entity.Person;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerTests {

    @Autowired
    private MockMvc mockmvc;

    @Test
    public void itShouldCreatePerson () throws Exception {
        Person person = new Person();
        person.setFirstName("Kevin");
        person.setLastName("Jean-Charles");
        mockmvc.perform(post("/person", person)).andExpect(status().is2xxSuccessful());
    }
}
