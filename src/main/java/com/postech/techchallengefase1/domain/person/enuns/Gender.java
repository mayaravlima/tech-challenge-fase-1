package com.postech.techchallengefase1.domain.person.enuns;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Gender {
    FEMALE,
    MALE;

    @JsonCreator
    public static Gender fromString(String name) {
        return name == null
                ? null
                : Gender.valueOf(name.toUpperCase());
    }
}
