package com.example.Convertly.service;

public interface UnitConversionService {
    double convert(String fromUnit, String toUnit, double value);

    String getFormula(String fromUnit, String toUnit, double value,double result);
}
