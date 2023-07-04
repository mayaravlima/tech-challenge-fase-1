package com.postech.techchallengefase1.domain.appliance.repository;

import com.postech.techchallengefase1.domain.appliance.entity.Appliance;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.Set;

@Repository
public class ApplianceRepository {

    private final Set<Appliance> appliances;

    public ApplianceRepository() {
        appliances = new HashSet<>();
    }
    public Boolean save(Appliance appliance) {
        appliance.setId(getNextId());
        return appliances.add(appliance);
    }

    public Long getNextId() {
        OptionalLong maxId = appliances.stream()
                .mapToLong(Appliance::getId)
                .max();
        return maxId.orElse(0) + 1;
    }

    public Set<Appliance> getAppliances() {
        return appliances;
    }

    public Appliance getApplianceById(Long id) {
        return appliances.stream()
                .filter(appliance -> appliance.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Boolean deleteApplianceById(Long id) {
        return appliances.removeIf(appliance -> appliance.getId().equals(id));
    }

    public Optional<Appliance> updateAppliance(Appliance appliance) {
        Optional<Appliance> applianceToUpdate = appliances.stream()
                .filter(appliance1 -> appliance1.getId().equals(appliance.getId()))
                .findFirst();
        if (applianceToUpdate.isPresent()) {
            Appliance a = applianceToUpdate.get();
            appliances.remove(a);
            a.setName(appliance.getName());
            a.setPower(appliance.getPower());
            a.setBrand(appliance.getBrand());
            a.setModel(appliance.getModel());
            appliances.add(a);
        }
        return applianceToUpdate;
    }
}
