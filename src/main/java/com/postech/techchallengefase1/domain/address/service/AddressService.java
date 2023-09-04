package com.postech.techchallengefase1.domain.address.service;

import com.postech.techchallengefase1.domain.address.dto.AddressDTO;
import com.postech.techchallengefase1.domain.address.dto.AddressResponseDTO;
import com.postech.techchallengefase1.domain.address.dto.CreateAddressDTO;
import com.postech.techchallengefase1.domain.address.dto.UpdateAddressDTO;
import com.postech.techchallengefase1.domain.address.entity.Address;
import com.postech.techchallengefase1.domain.address.repository.AddressRepository;
import com.postech.techchallengefase1.domain.appliance.entity.Appliance;
import com.postech.techchallengefase1.domain.appliance.repository.ApplianceRepository;
import com.postech.techchallengefase1.domain.exception.ApiException;
import com.postech.techchallengefase1.domain.person.entity.Person;
import com.postech.techchallengefase1.domain.user.dto.UserDTO;
import com.postech.techchallengefase1.domain.user.entity.User;
import com.postech.techchallengefase1.domain.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    private final UserRepository userRepository;

    private final ApplianceRepository applianceRepository;


    private AddressResponseDTO createAddressResponse(Address address, User user) {
        return new AddressResponseDTO(
                AddressDTO.toDTO(address),
                UserDTO.toDTO(user),
                new HashSet<>(),
                new HashSet<>()
        );
    }

    public AddressResponseDTO saveAddress(CreateAddressDTO createAddressDTO, String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new ApiException("User not found", HttpStatus.NOT_FOUND.value()));

        Address address = createAddressDTO.getAddress();
        address.setUser(user);

        addressRepository.save(address);

        return createAddressResponse(address, user);
    }

    public AddressResponseDTO associateApplianceWithAddress(
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
        appliance.setAddress(address);

        applianceRepository.save(appliance);

        return AddressResponseDTO.toDto(address);
    }

    public List<AddressResponseDTO> getAllAddress() {
        List<Address> addresses = addressRepository.findAll();

        if (addresses.isEmpty()) {
            throw new ApiException("Addresses not found", HttpStatus.NOT_FOUND.value());
        }

        return addresses.stream().map(AddressResponseDTO::toDto).toList();
    }

    public AddressResponseDTO getAddressById(Long id) {
        Address address = addressRepository.findById(id).orElseThrow(() ->
                new ApiException("Address not found", HttpStatus.NOT_FOUND.value()));
        return AddressResponseDTO.toDto(address);
    }

    public List<AddressResponseDTO> getAddressByStreet(String street) {
        List<Address> addresses = addressRepository.findByStreetContainingIgnoreCase(street).orElseThrow(() ->
                new ApiException("Address not found", HttpStatus.NOT_FOUND.value()));

        return addresses.stream().map(AddressResponseDTO::toDto).toList();
    }

    public List<AddressResponseDTO> getAddressByNeighborhood(String neighborhood) {
        List<Address> addresses = addressRepository.findByNeighborhoodContainingIgnoreCase(neighborhood).orElseThrow(() ->
                new ApiException("Address not found", HttpStatus.NOT_FOUND.value()));

        return addresses.stream().map(AddressResponseDTO::toDto).toList();
    }

    public List<AddressResponseDTO> getAddressByCity(String city) {
        List<Address> addresses = addressRepository.findByCityContainingIgnoreCase(city).orElseThrow(() ->
                new ApiException("Address not found", HttpStatus.NOT_FOUND.value()));

        return addresses.stream().map(AddressResponseDTO::toDto).toList();
    }

    public List<AddressResponseDTO> getAddressByState(String state) {
        List<Address> addresses = addressRepository.findByStateContainingIgnoreCase(state).orElseThrow(() ->
                new ApiException("Address not found", HttpStatus.NOT_FOUND.value()));

        return addresses.stream().map(AddressResponseDTO::toDto).toList();
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

    public AddressResponseDTO updateAddress(UpdateAddressDTO updateAddressDTO, Long id) {
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

        return AddressResponseDTO.toDto(address);
    }

}
