package com.example.Convertly.service;

import com.example.Convertly.enums.Category;
import com.example.Convertly.enums.TimeUnit;
import com.example.Convertly.exception.InvalidUnitException;
import com.example.Convertly.model.ConversionRequest;
import com.example.Convertly.model.ConversionResponse;
import org.springframework.stereotype.Service;

@Service
public class ConversionService {
    private final TemperatureService temperatureService;
    private final LengthService lengthService;
    private final TimeService timeService;
    private final WeightService weightService;


    public ConversionService(TemperatureService temperatureService, LengthService lengthService, TimeService timeService, WeightService weightService) {
        this.temperatureService = temperatureService;
        this.lengthService = lengthService;
        this.timeService = timeService;
        this.weightService = weightService;
    }

    public ConversionResponse convert(ConversionRequest request){

        Category cat;
        try {
            cat = Category.valueOf(request.getCategory().trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid category: " + request.getCategory());
        }
        double result;
        String formula ="";

        switch (cat){
            case TEMPERATURE:
                 result =temperatureService.convert(request.getFromUnit(), request.getToUnit(), request.getValue());
                 formula = temperatureService.getFormula(request.getFromUnit(), request.getToUnit(), request.getValue(),result);
                 break;
            case LENGTH :
                 result = lengthService.convert(request.getFromUnit(), request.getToUnit(), request.getValue());
                 formula = lengthService.getFormula(request.getFromUnit(), request.getToUnit(), request.getValue(),result);
                 break;
            case WEIGHT:
                result = weightService.convert(request.getFromUnit(), request.getToUnit(), request.getValue());
                formula = weightService.getFormula(request.getFromUnit(), request.getToUnit(), request.getValue(),result);
                break;
            case TIME:
                result = timeService.convert(request.getFromUnit(), request.getToUnit(), request.getValue());
                formula = timeService.getFormula(request.getFromUnit(), request.getToUnit(), request.getValue(),result);
                break;
            default:
                throw new IllegalArgumentException("Unsupported category: " + request.getCategory());

        }

        return new ConversionResponse(result,formula,"success");
    }
}
