package com.postech.techchallengefase1.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode(exclude = {"id"})
@Getter
@Setter
@ToString
public class Address {

    private Long id;

    @NotNull(message = "Street can't be null")
    @Size(min = 2, max = 50, message = "Street must be between 3 and 50 characters")
    @JsonProperty
    private String street;

    @NotNull(message = "Number can't be null")
    @Positive(message = "Number must be more than 0")
    @JsonProperty
    private Long number;

    @JsonProperty
    private String complement;

    @NotNull(message = "Neighborhood can't be null")
    @Size(min = 2, max = 50, message = "Neighborhood must be between 3 and 50 characters")
    @JsonProperty
    private String neighborhood;

    @NotNull(message = "City can't be null")
    @Size(min = 2, max = 50, message = "City must be between 3 and 50 characters")
    @JsonProperty
    private String city;

    @NotNull(message = "State can't be null")
    @Size(min = 2, max = 50, message = "State must be between 3 and 50 characters")
    @JsonProperty
    private String state;
}
