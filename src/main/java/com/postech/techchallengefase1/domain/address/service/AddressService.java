package com.postech.techchallengefase1.domain.address.service;

import com.postech.techchallengefase1.domain.address.entity.Address;
import com.postech.techchallengefase1.domain.address.repository.AddressRepository;
import com.postech.techchallengefase1.domain.exception.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address saveAddress(Address address) {
        if(!addressRepository.save(address)) {
            throw new ApiException("Address already exists", HttpStatus.CONFLICT.value());
        }
        return address;
    }

    public Set<Address> getAllAddress() {
        return addressRepository.getAddresses();
    }

    public Address getAddressById(Long id) {
        Address address = addressRepository.getAddressById(id);
        if(address == null) {
            throw new ApiException("Address not found", HttpStatus.NOT_FOUND.value());
        }
        return addressRepository.getAddressById(id);
    }

    public void deleteAddressById(Long id) {
        if (!addressRepository.deleteAddressById(id)) {
            throw new ApiException("Address not found", HttpStatus.NOT_FOUND.value());
        }
    }

    public Address updateAddress(Address address) {
        return addressRepository.updateAddress(address).orElseThrow(() ->
                new ApiException("Address not found", HttpStatus.NOT_FOUND.value()));
    }

}
