package com.postech.techchallengefase1.domain.appliance.service;

import com.postech.techchallengefase1.domain.appliance.entity.Appliance;
import com.postech.techchallengefase1.domain.appliance.repository.ApplianceRepository;
import org.springframework.stereotype.Service;

@Service
public class ApplianceService {

    private final ApplianceRepository applianceRepository;

    public ApplianceService(ApplianceRepository applianceRepository) {
        this.applianceRepository = applianceRepository;
    }

    public Appliance saveAppliance(Appliance appliance) {
        return applianceRepository.save(appliance) ? appliance : null;
    }
}
