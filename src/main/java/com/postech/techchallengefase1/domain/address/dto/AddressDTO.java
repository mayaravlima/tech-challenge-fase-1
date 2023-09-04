package com.postech.techchallengefase1.domain.address.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

    private Long id;
    private String street;
    private Long number;
    private String complement;
    private String neighborhood;
    private String city;
    private String state;
    private String zipCode;

}
