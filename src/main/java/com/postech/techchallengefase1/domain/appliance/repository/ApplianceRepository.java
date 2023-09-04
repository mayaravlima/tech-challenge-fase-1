package com.postech.techchallengefase1.domain.appliance.repository;

import com.postech.techchallengefase1.domain.appliance.entity.Appliance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplianceRepository extends JpaRepository<Appliance, Long> {
    Optional<List<Appliance>> findByNameContainingIgnoreCase(String name);

    Optional<List<Appliance>> findByBrandContainingIgnoreCase(String brand);

    Optional<List<Appliance>> findByModelContainingIgnoreCase(String model);

    Optional<List<Appliance>> findByPower(Long power);
}
