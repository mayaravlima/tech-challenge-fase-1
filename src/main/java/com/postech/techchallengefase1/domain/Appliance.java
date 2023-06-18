package com.postech.techchallengefase1.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(exclude = {"id"})
@Getter
@Setter
public class Appliance {

    private Long id;

    @NotNull(message = "Name can't be empty or null")
    @Size(min = 2, max = 50, message = "Name must be between 3 and 50 characters")
    @JsonProperty
    private String name;

    @NotNull(message = "Model can't be empty or null")
    @Size(min = 2, max = 50, message = "Model must be between 3 and 50 characters")
    @JsonProperty
    private String model;

    @NotNull(message = "Brand can't be empty or null")
    @Size(min = 2, max = 50, message = "Brand must be between 3 and 50 characters")
    @JsonProperty
    private String brand;

    @NotNull(message = "Power can't be empty or null")
    @Positive(message = "Power must be more than 0")
    @JsonProperty
    private Long power;
}
