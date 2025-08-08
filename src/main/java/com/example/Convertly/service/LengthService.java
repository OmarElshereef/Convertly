package com.example.Convertly.service;


import com.example.Convertly.enums.LengthUnit;
import org.springframework.stereotype.Service;

@Service
public class LengthService implements UnitConversionService {

    @Override
    public double convert(String fromUnit, String toUnit, double value) {
        LengthUnit from = LengthUnit.valueOf(fromUnit.trim().toUpperCase());
        LengthUnit to = LengthUnit.valueOf(toUnit.trim().toUpperCase());

        if (from == to) {
            return value;
        }


        double valueInMeters = switch (from) {
            case METER -> value;
            case KILOMETER -> value * 1000;
            case MILE -> value * 1609.344;
            case FOOT -> value * 0.3048;
            case INCH -> value * 0.0254;
        };

        double result;
        result = switch (to) {
            case METER -> valueInMeters;
            case KILOMETER -> valueInMeters / 1000;
            case MILE -> valueInMeters / 1609.344;
            case FOOT -> valueInMeters / 0.3048;
            case INCH -> valueInMeters / 0.0254;
        };


        return Math.round(result * 100d) / 100d;
    }

    @Override
    public String getFormula(String fromUnit, String toUnit, double value, double result) {
        fromUnit = fromUnit.trim().toUpperCase();
        toUnit = toUnit.trim().toUpperCase();

        if (fromUnit.equals(toUnit)) {
            return "No conversion needed (same units)";
        }


        if (fromUnit.equals("METER") && toUnit.equals("KILOMETER")) {
            return value + " m ÷ 1000 = " + result + " km";
        } else if (fromUnit.equals("KILOMETER") && toUnit.equals("METER")) {
            return value + " km × 1000 = " + result + " m";
        }
        else if (fromUnit.equals("METER") && toUnit.equals("MILE")) {
            return value + " m ÷ 1609.344 = " + result + " mi";
        } else if (fromUnit.equals("MILE") && toUnit.equals("METER")) {
            return value + " mi × 1609.344 = " + result + " m";
        }
        else if (fromUnit.equals("METER") && toUnit.equals("FOOT")) {
            return value + " m ÷ 0.3048 = " + result + " ft";
        } else if (fromUnit.equals("FOOT") && toUnit.equals("METER")) {
            return value + " ft × 0.3048 = " + result + " m";
        }
        else if (fromUnit.equals("METER") && toUnit.equals("INCH")) {
            return value + " m ÷ 0.0254 = " + result + " in";
        } else if (fromUnit.equals("INCH") && toUnit.equals("METER")) {
            return value + " in × 0.0254 = " + result + " m";
        }
        else if (fromUnit.equals("KILOMETER") && toUnit.equals("MILE")) {
            return value + " km ÷ 1.609344 = " + result + " mi";
        } else if (fromUnit.equals("MILE") && toUnit.equals("KILOMETER")) {
            return value + " mi × 1.609344 = " + result + " km";
        }
        else if (fromUnit.equals("KILOMETER") && toUnit.equals("FOOT")) {
            return value + " km × 3280.84 = " + result + " ft";
        } else if (fromUnit.equals("FOOT") && toUnit.equals("KILOMETER")) {
            return value + " ft ÷ 3280.84 = " + result + " km";
        }
        else if (fromUnit.equals("KILOMETER") && toUnit.equals("INCH")) {
            return value + " km × 39370.1 = " + result + " in";
        } else if (fromUnit.equals("INCH") && toUnit.equals("KILOMETER")) {
            return value + " in ÷ 39370.1 = " + result + " km";
        }
        else if (fromUnit.equals("MILE") && toUnit.equals("FOOT")) {
            return value + " mi × 5280 = " + result + " ft";
        } else if (fromUnit.equals("FOOT") && toUnit.equals("MILE")) {
            return value + " ft ÷ 5280 = " + result + " mi";
        }
        else if (fromUnit.equals("MILE") && toUnit.equals("INCH")) {
            return value + " mi × 63360 = " + result + " in";
        } else if (fromUnit.equals("INCH") && toUnit.equals("MILE")) {
            return value + " in ÷ 63360 = " + result + " mi";
        }
        else if (fromUnit.equals("FOOT") && toUnit.equals("INCH")) {
            return value + " ft × 12 = " + result + " in";
        } else if (fromUnit.equals("INCH") && toUnit.equals("FOOT")) {
            return value + " in ÷ 12 = " + result + " ft";
        }

        return "Formula not available for this conversion";
    }
}
