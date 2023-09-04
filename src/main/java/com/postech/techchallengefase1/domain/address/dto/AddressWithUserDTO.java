package com.postech.techchallengefase1.domain.address.dto;

import com.postech.techchallengefase1.domain.address.entity.Address;
import com.postech.techchallengefase1.domain.user.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressWithUserDTO {

    private Long id;
    private String street;
    private Long number;
    private String complement;
    private String neighborhood;
    private String city;
    private String state;
    private String zipCode;
    private UserDTO user;

    public static AddressWithUserDTO toDTO(Address address) {

        UserDTO userDTO = new UserDTO(address.getUser().getId(),
                address.getUser().getUsername(),
                address.getUser().getEmail());

        return new AddressWithUserDTO(
                address.getId(),
                address.getStreet(),
                address.getNumber(),
                address.getComplement(),
                address.getNeighborhood(),
                address.getCity(),
                address.getState(),
                address.getZipCode(),
                userDTO);
    }

}
