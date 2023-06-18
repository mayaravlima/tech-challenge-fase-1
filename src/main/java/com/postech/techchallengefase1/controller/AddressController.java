package com.postech.techchallengefase1.controller;

import com.postech.techchallengefase1.domain.Address;
import com.postech.techchallengefase1.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressService service;

    public AddressController(AddressService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Address address) {
        Address result = service.saveAddress(address);
        return result == null
                ? ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error", "This address already exists"))
                : ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}
