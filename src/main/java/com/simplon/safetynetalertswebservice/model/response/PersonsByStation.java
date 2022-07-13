package com.simplon.safetynetalertswebservice.model.response;

import com.simplon.safetynetalertswebservice.model.entity.Person;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonsByStation {
    private List<PersonByStation> personList;
    private int adultNumber;
    private int childNumber;


}
