package com.postech.techchallengefase1.domain.address.dto;

import com.postech.techchallengefase1.domain.address.entity.Address;
import com.postech.techchallengefase1.domain.person.dto.PersonDTO;
import com.postech.techchallengefase1.domain.user.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressWithUserAndPersonDTO {

    private String street;
    private Long number;
    private String complement;
    private String neighborhood;
    private String city;
    private String state;
    private String zipCode;
    private UserDTO user;

    private Set<PersonDTO> persons;

    public static AddressWithUserAndPersonDTO toDTO(Address address) {

        UserDTO userDTO = new UserDTO(address.getUser().getId(),
                address.getUser().getUsername(),
                address.getUser().getEmail());

        Set<PersonDTO> personDTOs = address.getPersons().stream().map(
                person -> new PersonDTO(person.getId(),
                        person.getName(),
                        person.getCpf(),
                        person.getDateOfBirth(),
                        person.getGender(),
                        person.getRelationship())).collect(Collectors.toSet());

        return new AddressWithUserAndPersonDTO(address.getStreet(),
                address.getNumber(),
                address.getComplement(),
                address.getNeighborhood(),
                address.getCity(),
                address.getState(),
                address.getZipCode(),
                userDTO,
                personDTOs);
    }

}
