package com.postech.techchallengefase1.domain.person.repository;

import com.postech.techchallengefase1.domain.person.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
