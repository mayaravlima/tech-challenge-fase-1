package com.postech.techchallengefase1.domain.address.dto;

import com.postech.techchallengefase1.domain.address.entity.Address;
import com.postech.techchallengefase1.domain.person.dto.PersonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressWithPersonDTO {
    private AddressDTO address;
    private Set<PersonDTO> person;

    public static AddressWithPersonDTO toDto(Address address) {
        return Objects.isNull(address) ? null : new AddressWithPersonDTO(
                AddressDTO.toDTO(address),
                address.getPersons().stream().map(PersonDTO::toDTO).collect(Collectors.toSet())
        );
    }
}
