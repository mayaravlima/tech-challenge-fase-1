package com.postech.techchallengefase1.domain.person.enuns;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Relationship {
    OWNER,
    PARENT,
    SPOUSE,
    SIBLING,
    PARTNER,
    RELATIVE,
    CHILD;

    @JsonCreator
    public static Relationship fromString(String name) {
        return name == null
                ? null
                : Relationship.valueOf(name.toUpperCase());
    }

}
