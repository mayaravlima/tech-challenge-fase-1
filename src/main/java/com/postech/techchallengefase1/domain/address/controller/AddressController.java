package com.postech.techchallengefase1.domain.address.controller;

import com.postech.techchallengefase1.domain.address.dto.AddressResponseDTO;
import com.postech.techchallengefase1.domain.address.dto.CreateAddressDTO;
import com.postech.techchallengefase1.domain.address.dto.UpdateAddressDTO;
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
    public ResponseEntity<AddressResponseDTO> save(@Valid @RequestBody CreateAddressDTO address, @RequestHeader String username) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveAddress(address, username));
    }

    @PostMapping("/{addressId}/appliance/{appliance}")
    public ResponseEntity<AddressResponseDTO> associateApplianceWithAddress(
            @RequestHeader String username,
            @PathVariable("addressId") Long addressId,
            @PathVariable("appliance") Long appliance) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.associateApplianceWithAddress(username, addressId, appliance));

    }

    @GetMapping
    public ResponseEntity<List<AddressResponseDTO>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllAddress());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAddressById(id));
    }

    @GetMapping("/search/street/{street}")
    public ResponseEntity<List<AddressResponseDTO>> searchByStreet(@PathVariable String street) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAddressByStreet(street));
    }

    @GetMapping("/search/neighborhood/{neighborhood}")
    public ResponseEntity<List<AddressResponseDTO>> searchByNeighborhood(@PathVariable String neighborhood) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAddressByNeighborhood(neighborhood));
    }

    @GetMapping("/search/city/{city}")
    public ResponseEntity<List<AddressResponseDTO>> searchByCity(@PathVariable String city) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAddressByCity(city));
    }

    @GetMapping("/search/state/{state}")
    public ResponseEntity<List<AddressResponseDTO>> searchByState(@PathVariable String state) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAddressByState(state));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressResponseDTO> update(@Valid @RequestBody UpdateAddressDTO address, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateAddress(address, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteById(@PathVariable Long id) {
        service.deleteAddressById(id);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Address deleted successfully"));
    }
}
