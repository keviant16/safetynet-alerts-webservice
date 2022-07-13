package com.simplon.safetynetalertswebservice.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="medicalRecord_seq")
    @SequenceGenerator(
            name="medicalRecord_seq",
            sequenceName="medicalRecord_sequence",
            allocationSize=20
    )
    private Long id;

    @Column(updatable = false)
    private String firstName;

    @Column(updatable = false)
    private String lastName;
    private String birthdate;

    @ElementCollection
    @CollectionTable(name="allergies")
    @Column(name="allergy_name")
    private List<String> allergies;

    @ElementCollection
    @CollectionTable(name="medications")
    @Column(name="medication_name")
    private List<String> medications;

    @OneToOne
    @JoinColumn(name = "person_id")
    @JsonIgnore
    private Person person;
}
