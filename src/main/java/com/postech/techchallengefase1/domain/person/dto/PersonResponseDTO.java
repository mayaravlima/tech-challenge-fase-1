package com.postech.techchallengefase1.domain.person.dto;

import com.postech.techchallengefase1.domain.address.dto.AddressWithApplianceDTO;
import com.postech.techchallengefase1.domain.person.entity.Person;
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
public class PersonResponseDTO {

    private PersonDTO person;
    private UserDTO user;
    private Set<AddressWithApplianceDTO> property;

    public static PersonResponseDTO toDto(Person person) {
        PersonDTO personDTO = PersonDTO.toDTO(person);
        UserDTO userDTO = UserDTO.toDTO(person.getUser());
        Set<AddressWithApplianceDTO> addressDTOs =
                person.getAddresses().stream().map(AddressWithApplianceDTO::toDto).collect(java.util.stream.Collectors.toSet());

        return new PersonResponseDTO(personDTO, userDTO, addressDTOs);
    }
}
