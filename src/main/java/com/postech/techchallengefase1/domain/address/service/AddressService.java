package com.postech.techchallengefase1.domain.address.service;

import com.postech.techchallengefase1.domain.address.dto.CreateAddressDTO;
import com.postech.techchallengefase1.domain.address.dto.UpdateAddressDTO;
import com.postech.techchallengefase1.domain.address.entity.Address;
import com.postech.techchallengefase1.domain.address.repository.AddressRepository;
import com.postech.techchallengefase1.domain.exception.ApiException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    public Address saveAddress(CreateAddressDTO address) {
        Address newAddress = addressRepository.save(address.getAddress());
        if (newAddress.getId() == null) {
            throw new ApiException("Address already exists", HttpStatus.CONFLICT.value());
        }
        return newAddress;
    }

    public List<Address> getAllAddress() {
        return addressRepository.findAll();
    }

    public Address getAddressById(Long id) {
        return addressRepository.findById(id).orElseThrow(() ->
                new ApiException("Address not found", HttpStatus.NOT_FOUND.value()));
    }

    public void deleteAddressById(Long id) {
        if (!addressRepository.existsById(id)) {
            throw new ApiException("Address not found", HttpStatus.NOT_FOUND.value());
        }
        addressRepository.deleteById(id);
    }

    public Address updateAddress(UpdateAddressDTO address) {
        Address addressToUpdate = addressRepository.findById(address.getId()).orElseThrow(() -> new ApiException("Address not found", HttpStatus.NOT_FOUND.value()));

        Address updatedAddress = new Address();
        updatedAddress.setId(address.getId());
        updatedAddress.setStreet(address.getStreet() != null ? address.getStreet() : addressToUpdate.getStreet());
        updatedAddress.setNumber(address.getNumber() != null ? address.getNumber() : addressToUpdate.getNumber());
        updatedAddress.setComplement(address.getComplement() != null ? address.getComplement() : addressToUpdate.getComplement());
        updatedAddress.setNeighborhood(address.getNeighborhood() != null ? address.getNeighborhood() : addressToUpdate.getNeighborhood());
        updatedAddress.setCity(address.getCity() != null ? address.getCity() : addressToUpdate.getCity());
        updatedAddress.setState(address.getState() != null ? address.getState() : addressToUpdate.getState());
        return addressRepository.save(updatedAddress);
    }

}
