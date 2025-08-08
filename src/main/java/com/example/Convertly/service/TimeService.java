package com.example.Convertly.service;

import com.example.Convertly.enums.LengthUnit;
import com.example.Convertly.enums.TemperatureUnit;
import com.example.Convertly.enums.TimeUnit;
import com.example.Convertly.exception.InvalidUnitException;
import org.springframework.stereotype.Service;

@Service
public class TimeService implements UnitConversionService {

    @Override
    public double convert(String fromUnit, String toUnit, double value) {
        TimeUnit from ;
        TimeUnit to ;

        try {
            from = TimeUnit.valueOf(fromUnit.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidUnitException("Unsupported time unit: " + fromUnit);
        }

        try {
            to = TimeUnit.valueOf(toUnit.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidUnitException("Unsupported time unit: " + toUnit);
        }

        if (from == to) {
            return value;
        }

        double result = getResult(value,from,to);

        return Math.round(result * 100d) / 100d;
    }

    private static double getResult(double value, TimeUnit from, TimeUnit to){
        double valueInSeconds = switch (from) {
            case SECOND -> value;
            case MINUTE -> value * 60;
            case HOUR -> value * 3600;
            case DAY -> value * 86400;
        };


        double result = switch (to) {
            case SECOND -> valueInSeconds;
            case MINUTE -> valueInSeconds / 60;
            case HOUR -> valueInSeconds / 3600;
            case DAY -> valueInSeconds / 86400;
        };

        return result;
    }

    @Override
    public String getFormula(String fromUnit, String toUnit, double value, double result) {
        fromUnit = fromUnit.trim().toUpperCase();
        toUnit = toUnit.trim().toUpperCase();

        if (fromUnit.equals("SECOND") && toUnit.equals("MINUTE")) {
            return value + " s ÷ 60 = " + result + " min";
        } else if (fromUnit.equals("MINUTE") && toUnit.equals("SECOND")) {
            return value + " min × 60 = " + result + " s";
        } else if (fromUnit.equals("SECOND") && toUnit.equals("HOUR")) {
            return value + " s ÷ 3600 = " + result + " h";
        } else if (fromUnit.equals("HOUR") && toUnit.equals("SECOND")) {
            return value + " h × 3600 = " + result + " s";
        } else if (fromUnit.equals("SECOND") && toUnit.equals("DAY")) {
            return value + " s ÷ 86400 = " + result + " d";
        } else if (fromUnit.equals("DAY") && toUnit.equals("SECOND")) {
            return value + " d × 86400 = " + result + " s";
        } else if (fromUnit.equals("MINUTE") && toUnit.equals("HOUR")) {
            return value + " min ÷ 60 = " + result + " h";
        } else if (fromUnit.equals("HOUR") && toUnit.equals("MINUTE")) {
            return value + " h × 60 = " + result + " min";
        } else if (fromUnit.equals("MINUTE") && toUnit.equals("DAY")) {
            return value + " min ÷ 1440 = " + result + " d";
        } else if (fromUnit.equals("DAY") && toUnit.equals("MINUTE")) {
            return value + " d × 1440 = " + result + " min";
        } else if (fromUnit.equals("HOUR") && toUnit.equals("DAY")) {
            return value + " h ÷ 24 = " + result + " d";
        } else if (fromUnit.equals("DAY") && toUnit.equals("HOUR")) {
            return value + " d × 24 = " + result + " h";
        } else if (fromUnit.equals(toUnit)) {
            return "No conversion needed (same units)";
        }

        return "Formula not available for this conversion";
    }
}