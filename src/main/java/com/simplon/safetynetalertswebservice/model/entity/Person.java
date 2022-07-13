package com.simplon.safetynetalertswebservice.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="person_seq")
    @SequenceGenerator(
            name="person_seq",
            sequenceName="person_sequence",
            allocationSize=20
    )
    private Long id;

    @Column(updatable = false)
    private String firstName;

    @Column(updatable = false)
    private String lastName;
    private String address;
    private String city;
    private String zip;
    private String phone;
    private String email;

    @OneToOne(mappedBy = "person",fetch = FetchType.LAZY)
    private MedicalRecord medicalRecord;
}
