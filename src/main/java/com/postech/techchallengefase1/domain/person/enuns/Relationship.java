package com.postech.techchallengefase1.domain.person.enuns;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Relationship {
    OWNER ("owner"),
    PARENT("parent"),
    SPOUSE("spouse"),
    SIBLING("sibling"),
    PARTNER("partner"),
    RELATIVE("relative");

    private final String name;

    Relationship(String name) {
        this.name = name;
    }

    @JsonCreator
    public static Relationship fromString(String name) {
        return name == null
                ? null
                : Relationship.valueOf(name.toUpperCase());
    }

    @JsonValue
    public String getName() {
        return this.name.toUpperCase();
    }

}
