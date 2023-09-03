package com.postech.techchallengefase1.domain.property.entity;

import com.postech.techchallengefase1.domain.address.entity.Address;
import com.postech.techchallengefase1.domain.appliance.entity.Appliance;
import com.postech.techchallengefase1.domain.person.entity.Person;
import com.postech.techchallengefase1.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "house")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

}
