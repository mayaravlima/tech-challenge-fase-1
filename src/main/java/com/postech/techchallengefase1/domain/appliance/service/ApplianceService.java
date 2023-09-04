package com.postech.techchallengefase1.domain.appliance.service;

import com.postech.techchallengefase1.domain.appliance.dto.*;
import com.postech.techchallengefase1.domain.appliance.entity.Appliance;
import com.postech.techchallengefase1.domain.appliance.repository.ApplianceRepository;
import com.postech.techchallengefase1.domain.exception.ApiException;
import com.postech.techchallengefase1.domain.user.entity.User;
import com.postech.techchallengefase1.domain.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ApplianceService {

    private final ApplianceRepository applianceRepository;

    private final UserRepository userRepository;

    public ApplianceWithUserDTO saveAppliance(CreateApplianceDTO createApplianceDTO, String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new ApiException("User not found", HttpStatus.NOT_FOUND.value()));

        Appliance appliance = createApplianceDTO.getAppliance();
        appliance.setUser(user);

        applianceRepository.save(appliance);

        return ApplianceWithUserDTO.toDto(appliance);
    }

    public List<ApplianceWithUserAndAddressDTO> getAllAppliance() {
        List<Appliance> appliances = applianceRepository.findAll();

        if (appliances.isEmpty()) {
            throw new ApiException("Appliances not found", HttpStatus.NOT_FOUND.value());
        }

        return appliances.stream().map(ApplianceWithUserAndAddressDTO::toDto).toList();
    }

    public ApplianceWithUserAndAddressDTO getApplianceById(Long id) {
        Appliance appliance = applianceRepository.findById(id).orElseThrow(() ->
                new ApiException("Appliance not found", HttpStatus.NOT_FOUND.value()));
        return ApplianceWithUserAndAddressDTO.toDto(appliance);
    }

    public List<ApplianceWithUserAndAddressDTO> getApplianceByName(String name) {
        List<Appliance> appliances = applianceRepository.findByName(name).orElseThrow(() ->
                new ApiException("Appliance not found", HttpStatus.NOT_FOUND.value()));
        return appliances.stream().map(ApplianceWithUserAndAddressDTO::toDto).toList();
    }

    public List<ApplianceWithUserAndAddressDTO> getApplianceByBrand(String brand) {
        List<Appliance> appliances = applianceRepository.findByBrand(brand).orElseThrow(() ->
                new ApiException("Appliance not found", HttpStatus.NOT_FOUND.value()));
        return appliances.stream().map(ApplianceWithUserAndAddressDTO::toDto).toList();
    }

    public List<ApplianceWithUserAndAddressDTO> getApplianceByModel(String model) {
        List<Appliance> appliances = applianceRepository.findByModel(model).orElseThrow(() ->
                new ApiException("Appliance not found", HttpStatus.NOT_FOUND.value()));
        return appliances.stream().map(ApplianceWithUserAndAddressDTO::toDto).toList();
    }

    public List<ApplianceWithUserAndAddressDTO> getApplianceByPower(Long power) {
        List<Appliance> appliances = applianceRepository.findByPower(power).orElseThrow(() ->
                new ApiException("Appliance not found", HttpStatus.NOT_FOUND.value()));
        return appliances.stream().map(ApplianceWithUserAndAddressDTO::toDto).toList();
    }

    public void deleteApplianceById(Long id) {
        if (!applianceRepository.existsById(id)) {
            throw new ApiException("Appliance not found", HttpStatus.NOT_FOUND.value());
        }
        applianceRepository.deleteById(id);
    }

    private String updateIfNotNull(String newValue, String currentValue) {
        return newValue != null ? newValue : currentValue;
    }

    private Long updateIfNotNull(Long newValue, Long currentValue) {
        return newValue != null ? newValue : currentValue;
    }

    public ApplianceWithUserAndAddressDTO updateAppliance(UpdateApplianceDTO updateApplianceDTO, Long id) {
        Appliance appliance = applianceRepository.findById(id).orElseThrow(() ->
                new ApiException("Appliance not found", HttpStatus.NOT_FOUND.value()));

        if (updateApplianceDTO.getName() != null) {
            appliance.setName(updateApplianceDTO.getName());
        }
        if (updateApplianceDTO.getBrand() != null) {
            appliance.setBrand(updateApplianceDTO.getBrand());
        }
        if (updateApplianceDTO.getModel() != null) {
            appliance.setModel(updateApplianceDTO.getModel());
        }
        if (updateApplianceDTO.getPower() != null) {
            appliance.setPower(updateApplianceDTO.getPower());
        }

        applianceRepository.save(appliance);

        return ApplianceWithUserAndAddressDTO.toDto(appliance);
    }

    public String calculateEnergyConsumption(CalculateConsumptionRequest request) {
        Appliance appliance = applianceRepository.findById(request.getApplicationId()).orElseThrow(() ->
                new ApiException("Appliance not found", HttpStatus.NOT_FOUND.value()));

        double energyConsumption = (appliance.getPower() * request.getHoursOfUse()) / 1000.0;
        return "Energy consumption: " + energyConsumption + " kWh";
    }
}
