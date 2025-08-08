package com.example.Convertly.controller;


import com.example.Convertly.enums.*;
import com.example.Convertly.model.ConversionRequest;
import com.example.Convertly.model.ConversionResponse;
import com.example.Convertly.service.ConversionService;
import com.example.Convertly.service.TemperatureService;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
public class ConverterController {


    private final ConversionService conversionService;

    public ConverterController(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @PostMapping("/convert")
    public ConversionResponse convert(@RequestBody ConversionRequest request){
        return conversionService.convert(request);
    }

    @GetMapping("/categories")
    public List<String> getCategories() {
        return Arrays.stream(Category.values())
                .map(c -> c.name().toLowerCase())
                .toList();
    }

    @GetMapping("/units")
    public List<String> getUnits(@RequestParam String category) {
        Category cat = Category.fromString(category);

        return switch (cat) {
            case TEMPERATURE -> Arrays.stream(TemperatureUnit.values()).map(u -> u.name().toLowerCase()).toList();
            case LENGTH -> Arrays.stream(LengthUnit.values()).map(u -> u.name().toLowerCase()).toList();
            case WEIGHT -> Arrays.stream(WeightUnit.values()).map(u -> u.name().toLowerCase()).toList();
            case TIME -> Arrays.stream(TimeUnit.values()).map(u -> u.name().toLowerCase()).toList();
        };
    }

    @GetMapping("/sample-payload")
    public Map<String, Object> getSamplePayload() {
        return Map.of(
                "category", "temperature",
                "fromUnit", "celsius",
                "toUnit", "fahrenheit",
                "value", 25
        );
    }

    @GetMapping("/health")
    public Map<String, Object> healthCheck() {
        boolean conversionWorking;
        try {
            double test = new TemperatureService().convert("celsius", "fahrenheit", 1);
            conversionWorking = test != 0;
        } catch (Exception e) {
            conversionWorking = false;
        }

        return Map.of(
                "status", conversionWorking ? "OK" : "FAIL",
                "conversionService", conversionWorking ? "Available" : "Error",
                "timestamp", System.currentTimeMillis()
        );
    }


}
