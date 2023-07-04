package com.postech.techchallengefase1.domain.address.controller;

import com.postech.techchallengefase1.domain.address.service.AddressService;
import com.postech.techchallengefase1.domain.address.entity.Address;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressService service;

    public AddressController(AddressService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Address> save(@Valid @RequestBody Address address) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveAddress(address));
    }

    @GetMapping
    public ResponseEntity<Set<Address>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllAddress());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAddressById(id));
    }

    @PutMapping
    public ResponseEntity<Address> update(@Valid @RequestBody Address address) {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateAddress(address));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteById(@PathVariable Long id) {
        service.deleteAddressById(id);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Address deleted successfully"));
    }
}
