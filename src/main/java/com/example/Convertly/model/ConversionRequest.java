package com.example.Convertly.model;

import com.example.Convertly.enums.Category;
import jakarta.validation.constraints.NotNull;

public class ConversionRequest {

    @NotNull
    private String category;

    @NotNull
    private String fromUnit;

    @NotNull
    private String toUnit;

    @NotNull
    private Double value;

    public String getCategory() {
        return category;
    }

    public String getFromUnit() {
        return fromUnit;
    }

    public String getToUnit() {
        return toUnit;
    }

    public Double getValue() {
        return value;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setFromUnit(String fromUnit) {
        this.fromUnit = fromUnit;
    }

    public void setToUnit(String toUnit) {
        this.toUnit = toUnit;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
