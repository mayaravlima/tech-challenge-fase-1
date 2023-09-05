package com.postech.techchallengefase1.domain.appliance.dto;

import com.postech.techchallengefase1.domain.appliance.entity.Appliance;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplianceDTO {
    private Long id;
    private String name;
    private String model;
    private String brand;
    private Long power;

    public static ApplianceDTO toDTO(Appliance appliance) {
        return new ApplianceDTO(
                appliance.getId(),
                appliance.getName(),
                appliance.getModel(),
                appliance.getBrand(),
                appliance.getPower());
    }
}
