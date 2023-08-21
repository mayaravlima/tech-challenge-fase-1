package com.postech.techchallengefase1.domain.address.dto;

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
public class UpdateAddressDTO {

    @NotNull(message = "Id can't be null")
    @Positive(message = "Id must be more than 0")
    private Long id;

    @Size(min = 2, max = 50, message = "Street must be between 3 and 50 characters")
    private String street;

    @Positive(message = "Number must be more than 0")
    private Long number;

    private String complement;

    @Size(min = 2, max = 50, message = "Neighborhood must be between 3 and 50 characters")
    private String neighborhood;


    @Size(min = 2, max = 50, message = "City must be between 3 and 50 characters")
    private String city;

    @Size(min = 2, max = 50, message = "State must be between 3 and 50 characters")
    private String state;

}
