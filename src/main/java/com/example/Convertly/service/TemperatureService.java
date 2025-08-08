package com.example.Convertly.service;

import com.example.Convertly.enums.TemperatureUnit;
import org.springframework.stereotype.Service;

@Service
public class TemperatureService implements UnitConversionService {

    @Override
    public double convert(String fromUnit, String toUnit, double value) {
        TemperatureUnit from = TemperatureUnit.valueOf(fromUnit.toUpperCase());
        TemperatureUnit to = TemperatureUnit.valueOf(toUnit.toUpperCase());

        if (from == to){
            return value;
        }

        double valueInCelsius;
        switch (from) {
            case CELSIUS:
                valueInCelsius = value;
                break;
            case FAHRENHEIT:
                valueInCelsius = (value - 32) * 5 / 9;
                break;
            case KELVIN:
                valueInCelsius = value - 273.15;
                break;
            default:
                throw new IllegalArgumentException("Unsupported temperature unit: " + fromUnit);
        }

        double result =switch (to) {
            case CELSIUS-> valueInCelsius;
            case FAHRENHEIT-> (valueInCelsius * 9 / 5) + 32;
            case KELVIN-> valueInCelsius + 273.15;

        };

        return Math.round(result * 100d) / 100d;

    }

    @Override
    public String getFormula(String fromUnit, String toUnit, double value,double result) {

        fromUnit = fromUnit.trim().toUpperCase();
        toUnit = toUnit.trim().toUpperCase();


        if (fromUnit.equals("CELSIUS") && toUnit.equals("FAHRENHEIT")) {
            return "(" + value + "°C × 9/5) + 32 = " + result + "°F";
        } else if (fromUnit.equals("FAHRENHEIT") && toUnit.equals("CELSIUS")) {
            return "(" + value + "°F − 32) × 5/9 = " + result + "°C";
        } else if (fromUnit.equals("CELSIUS") && toUnit.equals("KELVIN")) {
            return value + "°C + 273.15 = " + result + "K";
        } else if (fromUnit.equals("KELVIN") && toUnit.equals("CELSIUS")) {
            return value + "K − 273.15 = " + result + "°C";
        } else if (fromUnit.equals("FAHRENHEIT") && toUnit.equals("KELVIN")) {
            return "((" + value + "°F − 32) × 5/9) + 273.15 = " + result + "K";
        } else if (fromUnit.equals("KELVIN") && toUnit.equals("FAHRENHEIT")) {
            return "((" + value + "K − 273.15) × 9/5) + 32 = " + result + "°F";
        } else if (fromUnit.equals(toUnit)) {
            return "No conversion needed (same units)";
        }

        return "Formula not available for this conversion";
    }
}
