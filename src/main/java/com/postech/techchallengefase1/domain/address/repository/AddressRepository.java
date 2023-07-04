package com.postech.techchallengefase1.domain.address.repository;

import com.postech.techchallengefase1.domain.address.entity.Address;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Optional;
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

    public Set<Address> getAddresses() {
        return addresses;
    }

    public Address getAddressById(Long id) {
        return addresses.stream()
                .filter(address -> address.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Boolean deleteAddressById(Long id) {
        return addresses.removeIf(address -> address.getId().equals(id));
    }

    public Optional<Address> updateAddress(Address address) {
        Optional<Address> addressToUpdate = addresses.stream()
                .filter(address1 -> address1.getId().equals(address.getId()))
                .findFirst();
        if (addressToUpdate.isPresent()) {
            Address a = addressToUpdate.get();
            addresses.remove(a);
            a.setStreet(address.getStreet());
            a.setNumber(address.getNumber());
            a.setComplement(address.getComplement());
            a.setNeighborhood(address.getNeighborhood());
            a.setCity(address.getCity());
            a.setState(address.getState());
            addresses.add(a);

            return addressToUpdate;
        }
        return Optional.empty();
    }
}
