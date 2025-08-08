package com.example.Convertly.model;

public class ConversionResponse {
    private double result;
    private String formula;
    private String status;

    public ConversionResponse(double result, String formula, String status) {
        this.result = result;
        this.formula = formula;
        this.status = status;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
