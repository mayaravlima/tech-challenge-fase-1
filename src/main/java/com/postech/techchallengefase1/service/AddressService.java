package com.postech.techchallengefase1.service;

import com.postech.techchallengefase1.domain.Address;
import com.postech.techchallengefase1.repository.AddressRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    private final AddressRepository addressRepository;


    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address saveAddress(Address address) {
        return addressRepository.save(address) ? address : null;
    }
}
