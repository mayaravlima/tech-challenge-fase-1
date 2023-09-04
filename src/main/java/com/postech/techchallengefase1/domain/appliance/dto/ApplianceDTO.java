package com.postech.techchallengefase1.domain.appliance.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplianceDTO {
    private String name;
    private String model;
    private String brand;
    private Long power;
}
