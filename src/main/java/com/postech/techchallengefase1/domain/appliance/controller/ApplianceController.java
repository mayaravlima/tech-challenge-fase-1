package com.postech.techchallengefase1.domain.appliance.controller;

import com.postech.techchallengefase1.domain.appliance.entity.Appliance;
import com.postech.techchallengefase1.domain.appliance.service.ApplianceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/appliance")
public class ApplianceController {

    private final ApplianceService service;

    public ApplianceController(ApplianceService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Appliance appliance) {
        Appliance result = service.saveAppliance(appliance);
        return result == null ?
                ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error", "This appliance already exists")) :
                ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}
