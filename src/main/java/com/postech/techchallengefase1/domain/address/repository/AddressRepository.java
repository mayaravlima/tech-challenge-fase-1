package com.postech.techchallengefase1.domain.address.repository;

import com.postech.techchallengefase1.domain.address.entity.Address;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.OptionalLong;
import java.util.Set;

@Repository
public class AddressRepository {

    private final Set<Address> addresses;

    public AddressRepository() {
        addresses = new HashSet<>();
    }

    public Boolean save(Address address) {
        address.setId(getNextId());
        return addresses.add(address);
    }

    public Long getNextId() {
        OptionalLong maxId = addresses.stream()
                .mapToLong(Address::getId)
                .max();
        return maxId.orElse(0) + 1;
    }
}
