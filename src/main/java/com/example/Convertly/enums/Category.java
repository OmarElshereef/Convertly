package com.example.Convertly.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Category {
    TEMPERATURE,
    LENGTH,
    WEIGHT,
    TIME;


    @JsonCreator
    public static Category fromString(String value) {
        return Category.valueOf(value.trim().toUpperCase());
    }
}
