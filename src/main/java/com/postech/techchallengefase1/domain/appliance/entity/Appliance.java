package com.postech.techchallengefase1.domain.appliance.entity;

import com.postech.techchallengefase1.domain.property.entity.Property;
import com.postech.techchallengefase1.domain.person.entity.Person;
import com.postech.techchallengefase1.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "appliance")
public class Appliance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "power", nullable = false)
    private Long power;

}
