package com.postech.techchallengefase1.domain.person.repository;

import com.postech.techchallengefase1.domain.person.entity.Person;
import com.postech.techchallengefase1.domain.person.enuns.Gender;
import com.postech.techchallengefase1.domain.person.enuns.Relationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<List<Person>> findByNameContainingIgnoreCase(String name);

    Optional<List<Person>> findByRelationship(Relationship relationship);

    Optional<List<Person>> findByGender(Gender gender);

    Optional<List<Person>> findByCpfContaining(String cpf);

    boolean existsByCpf(String cpf);
}
