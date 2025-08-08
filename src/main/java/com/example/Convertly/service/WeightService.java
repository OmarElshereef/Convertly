package com.example.Convertly.service;

import com.example.Convertly.enums.WeightUnit;
import org.springframework.stereotype.Service;

@Service
public class WeightService implements UnitConversionService {

    @Override
    public double convert(String fromUnit, String toUnit, double value) {
        WeightUnit from = WeightUnit.valueOf(fromUnit.trim().toUpperCase());
        WeightUnit to = WeightUnit.valueOf(toUnit.trim().toUpperCase());

        if (from == to) {
            return value;
        }


        double valueInGrams = switch (from) {
            case GRAM -> value;
            case KILOGRAM -> value * 1000;
            case POUND -> value * 453.59237;
            case OUNCE -> value * 28.3495231;
        };


        double result = switch (to) {
            case GRAM -> valueInGrams;
            case KILOGRAM -> valueInGrams / 1000;
            case POUND -> valueInGrams / 453.59237;
            case OUNCE -> valueInGrams / 28.3495231;
        };


        return Math.round(result * 100d) / 100d;
    }

    @Override
    public String getFormula(String fromUnit, String toUnit, double value, double result) {
        fromUnit = fromUnit.trim().toUpperCase();
        toUnit = toUnit.trim().toUpperCase();

        if (fromUnit.equals("GRAM") && toUnit.equals("KILOGRAM")) {
            return value + " g ÷ 1000 = " + result + " kg";
        } else if (fromUnit.equals("KILOGRAM") && toUnit.equals("GRAM")) {
            return value + " kg × 1000 = " + result + " g";
        } else if (fromUnit.equals("GRAM") && toUnit.equals("POUND")) {
            return value + " g ÷ 453.59237 = " + result + " lb";
        } else if (fromUnit.equals("POUND") && toUnit.equals("GRAM")) {
            return value + " lb × 453.59237 = " + result + " g";
        } else if (fromUnit.equals("GRAM") && toUnit.equals("OUNCE")) {
            return value + " g ÷ 28.3495231 = " + result + " oz";
        } else if (fromUnit.equals("OUNCE") && toUnit.equals("GRAM")) {
            return value + " oz × 28.3495231 = " + result + " g";
        } else if (fromUnit.equals("KILOGRAM") && toUnit.equals("POUND")) {
            return value + " kg × 2.20462262 = " + result + " lb";
        } else if (fromUnit.equals("POUND") && toUnit.equals("KILOGRAM")) {
            return value + " lb ÷ 2.20462262 = " + result + " kg";
        } else if (fromUnit.equals("KILOGRAM") && toUnit.equals("OUNCE")) {
            return value + " kg × 35.2739619 = " + result + " oz";
        } else if (fromUnit.equals("OUNCE") && toUnit.equals("KILOGRAM")) {
            return value + " oz ÷ 35.2739619 = " + result + " kg";
        } else if (fromUnit.equals("POUND") && toUnit.equals("OUNCE")) {
            return value + " lb × 16 = " + result + " oz";
        } else if (fromUnit.equals("OUNCE") && toUnit.equals("POUND")) {
            return value + " oz ÷ 16 = " + result + " lb";
        } else if (fromUnit.equals(toUnit)) {
            return "No conversion needed (same units)";
        }

        return "Formula not available for this conversion";
    }
}