package com.amarkelovua.rickandmortyapi.model;

public enum Gender {
    FEMALE("Female"),
    MALE("Male"),
    GENDERLESS("Genderless"),
    UNKNOWN("unknown");

    private final String description;

    private Gender(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
