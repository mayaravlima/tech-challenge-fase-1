package com.postech.techchallengefase1.domain.appliance.controller;

import com.postech.techchallengefase1.domain.appliance.dto.ApplianceResponseDTO;
import com.postech.techchallengefase1.domain.appliance.dto.CalculateConsumptionRequest;
import com.postech.techchallengefase1.domain.appliance.dto.CreateApplianceDTO;
import com.postech.techchallengefase1.domain.appliance.dto.UpdateApplianceDTO;
import com.postech.techchallengefase1.domain.appliance.service.ApplianceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/appliance")
public class ApplianceController {

    private final ApplianceService service;

    public ApplianceController(ApplianceService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApplianceResponseDTO> save(@Valid @RequestBody CreateApplianceDTO appliance, @RequestHeader String username) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveAppliance(appliance, username));
    }

    @GetMapping
    public ResponseEntity<List<ApplianceResponseDTO>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllAppliance());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplianceResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getApplianceById(id));
    }

    @GetMapping("/search/brand/{brand}")
    public ResponseEntity<List<ApplianceResponseDTO>> searchByBrand(@PathVariable String brand) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getApplianceByBrand(brand));
    }

    @GetMapping("/search/model/{model}")
    public ResponseEntity<List<ApplianceResponseDTO>> searchByModel(@PathVariable String model) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getApplianceByModel(model));
    }

    @GetMapping("/search/name/{name}")
    public ResponseEntity<List<ApplianceResponseDTO>> searchByName(@PathVariable String name) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getApplianceByName(name));
    }

    @GetMapping("/search/power/{power}")
    public ResponseEntity<List<ApplianceResponseDTO>> searchByPower(@PathVariable Long power) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getApplianceByPower(power));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApplianceResponseDTO> update(@Valid @RequestBody UpdateApplianceDTO appliance, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateAppliance(appliance, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteById(@PathVariable Long id) {
        service.deleteApplianceById(id);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Appliance deleted successfully"));
    }

    @GetMapping("/calculate")
    public ResponseEntity<Map<String, String>> calculate(@RequestBody CalculateConsumptionRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("Energy consumption", service.calculateEnergyConsumption(request)));
    }

}
