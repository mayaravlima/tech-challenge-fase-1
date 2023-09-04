package com.postech.techchallengefase1.domain.address.dto;

import com.postech.techchallengefase1.domain.address.entity.Address;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
public class CreateAddressDTO {

    @NotNull(message = "Street can't be null")
    @Size(min = 2, max = 50, message = "Street must be between 3 and 50 characters")
    private String street;

    @NotNull(message = "Number can't be null")
    @Positive(message = "Number must be more than 0")
    private Long number;

    private String complement;

    @NotNull(message = "Neighborhood can't be null")
    @Size(min = 2, max = 50, message = "Neighborhood must be between 3 and 50 characters")
    private String neighborhood;

    @NotNull(message = "City can't be null")
    @Size(min = 2, max = 50, message = "City must be between 3 and 50 characters")
    private String city;

    @NotNull(message = "State can't be null")
    @Size(min = 2, max = 50, message = "State must be between 3 and 50 characters")
    private String state;

    @Pattern(regexp = "^[0-9]*$", message = "Zip Code must contain only numbers")
    @NotNull(message = "Zip Code can't be null")
    @Size(min = 2, max = 50, message = "Zip Code must be equal to 8 characters")
    private String zipCode;

    public Address getAddress() {
        Address address = new Address();
        address.setStreet(this.street);
        address.setNumber(this.number);
        address.setComplement(this.complement);
        address.setNeighborhood(this.neighborhood);
        address.setCity(this.city);
        address.setState(this.state);
        address.setZipCode(this.zipCode);
        return address;
    }

}
