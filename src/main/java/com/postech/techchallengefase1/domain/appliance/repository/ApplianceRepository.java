package com.postech.techchallengefase1.domain.appliance.repository;

import com.postech.techchallengefase1.domain.appliance.entity.Appliance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplianceRepository extends JpaRepository<Appliance, Long> {
}
