package com.postech.techchallengefase1.domain.appliance.dto;

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
public class ApplianceWithUserDTO {

    private String name;
    private String model;
    private String brand;
    private Long power;
    private UserDTO user;

    public static ApplianceWithUserDTO toDto(Appliance appliance) {
        UserDTO userDTO = new UserDTO(appliance.getUser().getId(),
                appliance.getUser().getUsername(),
                appliance.getUser().getEmail());
        return new ApplianceWithUserDTO(
                appliance.getName(),
                appliance.getModel(),
                appliance.getBrand(),
                appliance.getPower(),
                userDTO
        );
    }
}
