package com.postech.techchallengefase1.domain.appliance.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CalculateConsumptionRequest {

    @NotNull(message = "Application Id can't be empty or null")
    private Long applicationId;

    @NotNull(message = "Hours of use can't be empty or null")
    @Positive(message = "Hours of use must be more than 0")
    private Long hoursOfUse;
}
