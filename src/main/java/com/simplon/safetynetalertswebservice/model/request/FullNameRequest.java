package com.simplon.safetynetalertswebservice.model.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FullNameRequest {
    private String firstName;
    private String lastName;
}
