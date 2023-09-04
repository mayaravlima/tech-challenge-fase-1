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
    private Set<ApplianceDTO> appliances;

    public static AddressWithApplianceDTO toDto(Address address) {
        Set<ApplianceDTO> appliances = address.getAppliances().stream()
                .map(appliance -> new ApplianceDTO(
                        appliance.getName(),
                        appliance.getModel(),
                        appliance.getBrand(),
                        appliance.getPower()))
                .collect(java.util.stream.Collectors.toSet());
        return new AddressWithApplianceDTO(new AddressDTO(address.getId(),
                address.getStreet(),
                address.getNumber(),
                address.getComplement(),
                address.getNeighborhood(),
                address.getCity(),
                address.getState(),
                address.getZipCode()),
                appliances);
    }
}
