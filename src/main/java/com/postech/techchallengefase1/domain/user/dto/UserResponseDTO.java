package com.postech.techchallengefase1.domain.user.dto;

import com.postech.techchallengefase1.domain.address.dto.AddressDTO;
import com.postech.techchallengefase1.domain.appliance.dto.ApplianceDTO;
import com.postech.techchallengefase1.domain.person.dto.PersonDTO;
import com.postech.techchallengefase1.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

    private UserDTO user;
    private Set<PersonDTO> persons;
    private Set<AddressDTO> addresses;
    private Set<ApplianceDTO> appliances;

    public static UserResponseDTO toDTO(User user) {
        return new UserResponseDTO(
                UserDTO.toDTO(user),
                user.getPersons().stream().map(PersonDTO::toDTO).collect(java.util.stream.Collectors.toSet()),
                user.getAddresses().stream().map(AddressDTO::toDTO).collect(java.util.stream.Collectors.toSet()),
                user.getAppliances().stream().map(ApplianceDTO::toDTO).collect(java.util.stream.Collectors.toSet())
        );
    }
}
