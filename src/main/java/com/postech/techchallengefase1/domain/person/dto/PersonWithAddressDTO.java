package com.postech.techchallengefase1.domain.person.dto;

import com.postech.techchallengefase1.domain.address.dto.AddressDTO;
import com.postech.techchallengefase1.domain.person.entity.Person;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonWithAddressDTO {
    private PersonDTO person;
    private Set<AddressDTO> addresses;

    public static PersonWithAddressDTO toDto(Person person) {
        Set<AddressDTO> addresses = person.getAddresses().stream()
                .map(address -> new AddressDTO(address.getId(),
                        address.getStreet(),
                        address.getNumber(),
                        address.getComplement(),
                        address.getNeighborhood(),
                        address.getCity(),
                        address.getState(),
                        address.getZipCode()))
                .collect(java.util.stream.Collectors.toSet());

        return new PersonWithAddressDTO(new PersonDTO(person.getId(),
                person.getName(),
                person.getCpf(),
                person.getDateOfBirth(),
                person.getGender(),
                person.getRelationship()),
                addresses);
    }
}
