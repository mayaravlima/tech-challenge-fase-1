package com.postech.techchallengefase1.domain.address.controller;

import com.postech.techchallengefase1.domain.address.dto.*;
import com.postech.techchallengefase1.domain.address.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressService service;

    public AddressController(AddressService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AddressWithUserDTO> save(@Valid @RequestBody CreateAddressDTO address, @RequestHeader String username) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveAddress(address, username));
    }

    @PostMapping("/{addressId}/appliance/{appliance}")
    public ResponseEntity<AddressWithApplianceDTO> associateApplianceWithAddress(
            @RequestHeader String username,
            @PathVariable("addressId") Long addressId,
            @PathVariable("appliance") Long appliance) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.associateApplianceWithAddress(username, addressId, appliance));

    }

    @GetMapping
    public ResponseEntity<List<AddressWithUserAndPersonDTO>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllAddress());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressWithUserAndPersonDTO> getById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAddressById(id));
    }

    @GetMapping("/search/street/{street}")
    public ResponseEntity<List<AddressWithUserAndPersonDTO>> searchByStreet(@PathVariable String street) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAddressByStreet(street));
    }

    @GetMapping("/search/neighborhood/{neighborhood}")
    public ResponseEntity<List<AddressWithUserAndPersonDTO>> searchByNeighborhood(@PathVariable String neighborhood) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAddressByNeighborhood(neighborhood));
    }

    @GetMapping("/search/city/{city}")
    public ResponseEntity<List<AddressWithUserAndPersonDTO>> searchByCity(@PathVariable String city) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAddressByCity(city));
    }

    @GetMapping("/search/state/{state}")
    public ResponseEntity<List<AddressWithUserAndPersonDTO>> searchByState(@PathVariable String state) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAddressByState(state));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressWithUserAndPersonDTO> update(@Valid @RequestBody UpdateAddressDTO address, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateAddress(address, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteById(@PathVariable Long id) {
        service.deleteAddressById(id);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Address deleted successfully"));
    }
}
