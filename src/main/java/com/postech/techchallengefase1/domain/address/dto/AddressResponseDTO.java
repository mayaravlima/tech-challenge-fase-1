package com.postech.techchallengefase1.domain.address.dto;

import com.postech.techchallengefase1.domain.address.entity.Address;
import com.postech.techchallengefase1.domain.appliance.dto.ApplianceDTO;
import com.postech.techchallengefase1.domain.person.dto.PersonDTO;
import com.postech.techchallengefase1.domain.user.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponseDTO {
    private AddressDTO address;
    private UserDTO user;
    private Set<PersonDTO> persons;
    private Set<ApplianceDTO> appliances;

    public static AddressResponseDTO toDto(Address address) {
        AddressDTO addressDTO = AddressDTO.toDTO(address);
        UserDTO userDTO = UserDTO.toDTO(address.getUser());
        Set<PersonDTO> personDTOs = address.getPersons().stream().map(PersonDTO::toDTO).collect(java.util.stream.Collectors.toSet());
        Set<ApplianceDTO> applianceDTOs = address.getAppliances().stream().map(ApplianceDTO::toDTO).collect(java.util.stream.Collectors.toSet());

        return new AddressResponseDTO(addressDTO, userDTO, personDTOs, applianceDTOs);
    }

}
