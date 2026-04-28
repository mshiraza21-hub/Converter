package com.converter;

import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/convert")
public class ConverterController {

    @PostMapping
    public Map<String, Object> convert(
            @RequestParam double value,
            @RequestParam String type,
            @RequestParam String direction) {

        Map<String, Object> response = new HashMap<>();

        try {
            double result;

            switch (type) {
                case "temperature" -> {
                    if (direction.equals("forward")) {
                        // Celsius to Fahrenheit
                        result = (value * 9.0 / 5.0) + 32;
                    } else {
                        // Fahrenheit to Celsius
                        result = (value - 32) * 5.0 / 9.0;
                    }
                }
                case "distance" -> {
                    if (direction.equals("forward")) {
                        // KM to Miles
                        result = value * 0.621371;
                    } else {
                        // Miles to KM
                        result = value / 0.621371;
                    }
                }
                case "weight" -> {
                    if (direction.equals("forward")) {
                        // KG to Pounds
                        result = value * 2.20462;
                    } else {
                        // Pounds to KG
                        result = value / 2.20462;
                    }
                }
                default -> {
                    response.put("error", "Invalid conversion type");
                    return response;
                }
            }

            // Round to 4 decimal places
            result = Math.round(result * 10000.0) / 10000.0;
            response.put("result", result);

        } catch (Exception e) {
            response.put("error", "Server error: " + e.getMessage());
        }

        return response;
    }
}
