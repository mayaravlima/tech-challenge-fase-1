package com.postech.techchallengefase1.domain.address.repository;

import com.postech.techchallengefase1.domain.address.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    Optional<List<Address>> findByStreet(String street);

    Optional<List<Address>> findByNeighborhood(String neighborhood);

    Optional<List<Address>> findByCity(String city);

    Optional<List<Address>> findByState(String state);
}
