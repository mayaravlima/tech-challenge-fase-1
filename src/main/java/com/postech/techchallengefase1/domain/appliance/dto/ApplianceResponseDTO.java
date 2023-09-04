package com.postech.techchallengefase1.domain.appliance.dto;

import com.postech.techchallengefase1.domain.address.dto.AddressWithPersonDTO;
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
public class ApplianceResponseDTO {
    private ApplianceDTO appliance;
    private UserDTO user;
    private AddressWithPersonDTO property;

    public static ApplianceResponseDTO toDto(Appliance appliance) {
        return new ApplianceResponseDTO(
                ApplianceDTO.toDTO(appliance),
                UserDTO.toDTO(appliance.getUser()),
                AddressWithPersonDTO.toDto(appliance.getAddress())
        );
    }

}
