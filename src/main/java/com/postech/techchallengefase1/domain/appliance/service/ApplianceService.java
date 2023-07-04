package com.postech.techchallengefase1.domain.appliance.service;

import com.postech.techchallengefase1.domain.appliance.entity.Appliance;
import com.postech.techchallengefase1.domain.appliance.repository.ApplianceRepository;
import com.postech.techchallengefase1.domain.exception.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ApplianceService {

    private final ApplianceRepository applianceRepository;

    public ApplianceService(ApplianceRepository applianceRepository) {
        this.applianceRepository = applianceRepository;
    }

    public Appliance saveAppliance(Appliance appliance) {
        if(!applianceRepository.save(appliance)) {
            throw new ApiException("Appliance already exists", HttpStatus.CONFLICT.value());
        }
        return appliance;
    }

    public Set<Appliance> getAllAppliance() {
        return applianceRepository.getAppliances();
    }

    public Appliance getApplianceById(Long id) {
        Appliance appliance = applianceRepository.getApplianceById(id);
        if(appliance == null) {
            throw new ApiException("Appliance not found", HttpStatus.NOT_FOUND.value());
        }
        return applianceRepository.getApplianceById(id);
    }

    public void deleteApplianceById(Long id) {
        if (!applianceRepository.deleteApplianceById(id)) {
            throw new ApiException("Appliance not found", HttpStatus.NOT_FOUND.value());
        }
    }

    public Appliance updateAppliance(Appliance appliance) {
        return applianceRepository.updateAppliance(appliance).orElseThrow(() ->
                new ApiException("Appliance not found", HttpStatus.NOT_FOUND.value()));
    }
}
