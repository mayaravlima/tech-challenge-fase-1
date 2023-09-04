package com.postech.techchallengefase1.domain.appliance.dto;

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
public class UpdateApplianceDTO {


    @Size(min = 2, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    @Size(min = 2, max = 50, message = "Model must be between 3 and 50 characters")
    private String model;

    @Size(min = 2, max = 50, message = "Brand must be between 3 and 50 characters")
    private String brand;

    @Positive(message = "Power must be more than 0")
    private Long power;
}
