package com.postech.techchallengefase1.domain.appliance.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.postech.techchallengefase1.domain.appliance.entity.Appliance;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateApplianceDTO {

    @NotNull(message = "Name can't be empty or null")
    @Size(min = 2, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    @NotNull(message = "Model can't be empty or null")
    @Size(min = 2, max = 50, message = "Model must be between 3 and 50 characters")
    private String model;

    @NotNull(message = "Brand can't be empty or null")
    @Size(min = 2, max = 50, message = "Brand must be between 3 and 50 characters")
    private String brand;

    @NotNull(message = "Power can't be empty or null")
    @Positive(message = "Power must be more than 0")
    private Long power;

    public Appliance getAppliance() {
        Appliance appliance = new Appliance();
        appliance.setName(this.name);
        appliance.setModel(this.model);
        appliance.setBrand(this.brand);
        appliance.setPower(this.power);
        return appliance;
    }
}
