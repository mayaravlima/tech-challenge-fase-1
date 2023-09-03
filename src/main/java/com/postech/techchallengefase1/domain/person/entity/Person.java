package com.postech.techchallengefase1.domain.person.entity;

import com.postech.techchallengefase1.domain.person.enuns.Gender;
import com.postech.techchallengefase1.domain.person.enuns.Relationship;
import com.postech.techchallengefase1.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "gender")
    private Gender gender;

    @Column(name = "relationship", nullable = false)
    private Relationship relationship;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Person(User user) {
        this.user = user;
    }


}
