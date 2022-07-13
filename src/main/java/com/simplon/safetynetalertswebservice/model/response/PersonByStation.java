package com.simplon.safetynetalertswebservice.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonByStation {
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
}
