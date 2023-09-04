package com.postech.techchallengefase1.domain.appliance.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CalculateConsumptionRequest {

    @NotNull(message = "Appliance Id can't be empty or null")
    @JsonProperty("appliance_id")
    private Long applianceId;

    @NotNull(message = "Hours of use can't be empty or null")
    @Positive(message = "Hours of use must be more than 0")
    @JsonProperty("hours_of_use")
    private Long hoursOfUse;
}
