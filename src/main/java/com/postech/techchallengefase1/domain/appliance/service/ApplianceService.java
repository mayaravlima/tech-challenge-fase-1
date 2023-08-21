package com.postech.techchallengefase1.domain.appliance.service;

import com.postech.techchallengefase1.domain.appliance.dto.CreateApplianceDTO;
import com.postech.techchallengefase1.domain.appliance.dto.UpdateApplianceDTO;
import com.postech.techchallengefase1.domain.appliance.entity.Appliance;
import com.postech.techchallengefase1.domain.appliance.repository.ApplianceRepository;
import com.postech.techchallengefase1.domain.exception.ApiException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ApplianceService {

    private final ApplianceRepository applianceRepository;

    public Appliance saveAppliance(CreateApplianceDTO appliance) {
        Appliance newAppliance = applianceRepository.save(appliance.getAppliance());
        if(newAppliance.getId() == null) {
            throw new ApiException("Appliance already exists", HttpStatus.CONFLICT.value());
        }
        return newAppliance;
    }

    public List<Appliance> getAllAppliance() {
        return applianceRepository.findAll();
    }

    public Appliance getApplianceById(Long id) {
        return applianceRepository.findById(id).orElseThrow(() ->
                new ApiException("Appliance not found", HttpStatus.NOT_FOUND.value()));
    }

    public void deleteApplianceById(Long id) {
        if (!applianceRepository.existsById(id)) {
            throw new ApiException("Appliance not found", HttpStatus.NOT_FOUND.value());
        }
        applianceRepository.deleteById(id);
    }

    public Appliance updateAppliance(UpdateApplianceDTO appliance) {
        Appliance applianceToUpdate = applianceRepository.findById(appliance.getId()).orElseThrow(() ->
                new ApiException("Appliance not found", HttpStatus.NOT_FOUND.value()));

        Appliance updatedAppliance = new Appliance();
        updatedAppliance.setId(applianceToUpdate.getId());
        updatedAppliance.setName(appliance.getName() != null ? appliance.getName() : applianceToUpdate.getName());
        updatedAppliance.setBrand(appliance.getBrand() != null ? appliance.getBrand() : applianceToUpdate.getBrand());
        updatedAppliance.setModel(appliance.getModel() != null ? appliance.getModel() : applianceToUpdate.getModel());
        updatedAppliance.setPower(appliance.getPower() != null ? appliance.getPower() : applianceToUpdate.getPower());
        return applianceRepository.save(updatedAppliance);
    }
}
