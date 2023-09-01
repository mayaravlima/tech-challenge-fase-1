package com.postech.techchallengefase1.domain.user.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.postech.techchallengefase1.domain.appliance.entity.Appliance;
import com.postech.techchallengefase1.domain.person.entity.Person;
import com.postech.techchallengefase1.domain.property.entity.Property;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "user1")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Person> person;

    @OneToMany(mappedBy = "user")
    private Set<Appliance> appliancesses;

    @ManyToOne
    @JoinColumn(name="property_id")
    private Property property;

}
