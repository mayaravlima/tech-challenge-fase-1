package com.postech.techchallengefase1.domain.address.service;

import com.postech.techchallengefase1.domain.address.dto.*;
import com.postech.techchallengefase1.domain.address.entity.Address;
import com.postech.techchallengefase1.domain.address.repository.AddressRepository;
import com.postech.techchallengefase1.domain.appliance.entity.Appliance;
import com.postech.techchallengefase1.domain.exception.ApiException;
import com.postech.techchallengefase1.domain.person.entity.Person;
import com.postech.techchallengefase1.domain.user.entity.User;
import com.postech.techchallengefase1.domain.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    private final UserRepository userRepository;

    public AddressWithUserDTO saveAddress(CreateAddressDTO createAddressDTO, String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new ApiException("User not found", HttpStatus.NOT_FOUND.value()));

        Address address = createAddressDTO.getAddress();
        address.setUser(user);

        addressRepository.save(address);

        return AddressWithUserDTO.toDTO(address);
    }

    public AddressWithApplianceDTO associateApplianceWithAddress(
            String username,
            Long addressId,
            Long applianceId) {
        Address address = addressRepository.findById(addressId).orElseThrow(() ->
                new ApiException("Address not found", HttpStatus.NOT_FOUND.value()));

        if (!address.getUser().getUsername().equals(username)) {
            throw new ApiException("Address not found", HttpStatus.NOT_FOUND.value());
        }

        Appliance appliance = address.getUser().getAppliances().stream()
                .filter(appliance1 -> Objects.equals(appliance1.getId(), applianceId))
                .findFirst()
                .orElseThrow(() -> new ApiException("Appliance not found", HttpStatus.NOT_FOUND.value()));

        address.getAppliances().add(appliance);

        addressRepository.save(address);

        return AddressWithApplianceDTO.toDto(address);
    }


    public List<AddressWithUserAndPersonDTO> getAllAddress() {
        List<Address> addresses = addressRepository.findAll();

        if (addresses.isEmpty()) {
            throw new ApiException("Addresses not found", HttpStatus.NOT_FOUND.value());
        }

        return addresses.stream().map(AddressWithUserAndPersonDTO::toDTO).toList();
    }

    public AddressWithUserAndPersonDTO getAddressById(Long id) {
        Address address = addressRepository.findById(id).orElseThrow(() ->
                new ApiException("Address not found", HttpStatus.NOT_FOUND.value()));
        return AddressWithUserAndPersonDTO.toDTO(address);
    }

    public List<AddressWithUserAndPersonDTO> getAddressByStreet(String street) {
        List<Address> addresses = addressRepository.findByStreet(street).orElseThrow(() ->
                new ApiException("Address not found", HttpStatus.NOT_FOUND.value()));

        return addresses.stream().map(AddressWithUserAndPersonDTO::toDTO).toList();
    }

    public List<AddressWithUserAndPersonDTO> getAddressByNeighborhood(String neighborhood) {
        List<Address> addresses = addressRepository.findByNeighborhood(neighborhood).orElseThrow(() ->
                new ApiException("Address not found", HttpStatus.NOT_FOUND.value()));

        return addresses.stream().map(AddressWithUserAndPersonDTO::toDTO).toList();
    }

    public List<AddressWithUserAndPersonDTO> getAddressByCity(String city) {
        List<Address> addresses = addressRepository.findByCity(city).orElseThrow(() ->
                new ApiException("Address not found", HttpStatus.NOT_FOUND.value()));

        return addresses.stream().map(AddressWithUserAndPersonDTO::toDTO).toList();
    }

    public List<AddressWithUserAndPersonDTO> getAddressByState(String state) {
        List<Address> addresses = addressRepository.findByState(state).orElseThrow(() ->
                new ApiException("Address not found", HttpStatus.NOT_FOUND.value()));

        return addresses.stream().map(AddressWithUserAndPersonDTO::toDTO).toList();
    }


    public void deleteAddressById(Long id) {
        Optional<Address> addressOptional = addressRepository.findById(id);
        if (addressOptional.isPresent()) {
            Address addressToDelete = addressOptional.get();
            Set<Person> persons = addressToDelete.getPersons();

            for (Person person : persons) {
                person.getAddresses().remove(addressToDelete);
            }
            addressRepository.delete(addressToDelete);
        } else {
            throw new ApiException("Address with id " + id + " not found", HttpStatus.NOT_FOUND.value());
        }
    }

    public AddressWithUserAndPersonDTO updateAddress(UpdateAddressDTO updateAddressDTO, Long id) {
        Address address = addressRepository.findById(id).orElseThrow(() -> new ApiException("Address not found", HttpStatus.NOT_FOUND.value()));

        if (updateAddressDTO.getStreet() != null) {
            address.setStreet(updateAddressDTO.getStreet());
        }
        if (updateAddressDTO.getNumber() != null) {
            address.setNumber(updateAddressDTO.getNumber());
        }
        if (updateAddressDTO.getComplement() != null) {
            address.setComplement(updateAddressDTO.getComplement());
        }
        if (updateAddressDTO.getNeighborhood() != null) {
            address.setNeighborhood(updateAddressDTO.getNeighborhood());
        }
        if (updateAddressDTO.getCity() != null) {
            address.setCity(updateAddressDTO.getCity());
        }
        if (updateAddressDTO.getState() != null) {
            address.setState(updateAddressDTO.getState());
        }
        if (updateAddressDTO.getZipCode() != null) {
            address.setZipCode(updateAddressDTO.getZipCode());
        }

        addressRepository.save(address);

        return AddressWithUserAndPersonDTO.toDTO(address);
    }

}
