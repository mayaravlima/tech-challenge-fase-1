package com.postech.techchallengefase1.domain.appliance.dto;

import com.postech.techchallengefase1.domain.address.dto.AddressDTO;
import com.postech.techchallengefase1.domain.appliance.entity.Appliance;
import com.postech.techchallengefase1.domain.user.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplianceWithUserAndAddressDTO {

    private ApplianceDTO appliance;
    private UserDTO user;
    private AddressDTO addresses;

    public static ApplianceWithUserAndAddressDTO toDto(Appliance appliance) {
        ApplianceDTO applianceDTO = new ApplianceDTO(
                appliance.getName(),
                appliance.getModel(),
                appliance.getBrand(),
                appliance.getPower());

        UserDTO userDTO = new UserDTO(
                appliance.getUser().getId(),
                appliance.getUser().getUsername(),
                appliance.getUser().getEmail());

        AddressDTO addressDTO = new AddressDTO(
                appliance.getAddress().getId(),
                appliance.getAddress().getStreet(),
                appliance.getAddress().getNumber(),
                appliance.getAddress().getComplement(),
                appliance.getAddress().getNeighborhood(),
                appliance.getAddress().getCity(),
                appliance.getAddress().getState(),
                appliance.getAddress().getZipCode());

        return new ApplianceWithUserAndAddressDTO(
                applianceDTO,
                userDTO,
                addressDTO);

    }
}
