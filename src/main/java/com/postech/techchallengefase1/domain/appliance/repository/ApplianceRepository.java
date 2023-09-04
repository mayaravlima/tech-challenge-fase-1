package com.postech.techchallengefase1.domain.appliance.repository;

import com.postech.techchallengefase1.domain.appliance.entity.Appliance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplianceRepository extends JpaRepository<Appliance, Long> {
    Optional<List<Appliance>> findByName(String name);

    Optional<List<Appliance>> findByBrand(String brand);

    Optional<List<Appliance>> findByModel(String model);

    Optional<List<Appliance>> findByPower(Long power);
}
