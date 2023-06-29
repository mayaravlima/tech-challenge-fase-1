package com.postech.techchallengefase1.domain.appliance.repository;

import com.postech.techchallengefase1.domain.appliance.entity.Appliance;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
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
}
