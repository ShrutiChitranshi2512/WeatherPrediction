package com.weatherprediction.controller;

import com.weatherprediction.exception.ResourceNotFoundException;
import com.weatherprediction.service.WeatherService;
import com.weatherprediction.util.Constants;
import com.weatherprediction.dto.response.WeatherPredictionData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/v1/weather")
public class WeatherController {
    private static final Logger LOG = LoggerFactory.getLogger(WeatherController.class);
    @Autowired
    private WeatherService weatherService;

    @Operation(summary = Constants.SWAGGER_WEATHER_FORECAST_CONTROLLER_DESC)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = Constants.MSG_DATA_FETCHED_SUCCESSFULLY),
            @ApiResponse(responseCode = "400", description = Constants.MSG_INVALID_INPUT),
            @ApiResponse(responseCode = "404", description = Constants.MSG_CITY_NOT_FOUND),
            @ApiResponse(responseCode = "500", description = Constants.MSG_INTERNAL_SERVER_ERROR)})
    @GetMapping("/{city}")
    public ResponseEntity<List<WeatherPredictionData>> getWeatherByCity
            (@PathVariable Optional<String> city) {
        LOG.trace("Entering getWeatherByCity(city={})", city);

        if (city == null || !city.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            return new ResponseEntity<List<WeatherPredictionData>>(
                    weatherService.getWeather(city.get()), HttpStatus.OK);
        } catch (Exception ex) {
            throw new ResourceNotFoundException(Constants.WEATHER_NOT_FOUND + city);
        }
    }

}
