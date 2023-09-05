package com.postech.techchallengefase1.domain.address.dto;

import com.postech.techchallengefase1.domain.address.entity.Address;
import com.postech.techchallengefase1.domain.appliance.dto.ApplianceDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressWithApplianceDTO {
    private AddressDTO address;
    private Set<ApplianceDTO> appliance;

    public static AddressWithApplianceDTO toDto(Address address) {
        return new AddressWithApplianceDTO(
                AddressDTO.toDTO(address),
                address.getAppliances().stream().map(ApplianceDTO::toDTO).collect(java.util.stream.Collectors.toSet())
        );
    }
}
