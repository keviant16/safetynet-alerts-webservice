package com.simplon.safetynetalertswebservice.model.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PersonInfo {
    private String firstName;
    private String lastName;
    private String birthdate;
    private String email;
    private List<String> medications;
    private List<String> allergies;
}
