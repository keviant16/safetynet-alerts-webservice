package com.simplon.safetynetalertswebservice.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FireStation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int station;

    @ElementCollection
    @CollectionTable(name="address")
    @Column(name="address_name")
    private List<String> addresses;
}
