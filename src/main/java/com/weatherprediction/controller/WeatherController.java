package com.weatherprediction.controller;

import com.weatherprediction.exception.ApiException;
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
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

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
    @GetMapping("/prediction")
    public ResponseEntity<List<WeatherPredictionData>> getWeatherByCity(
    @RequestParam @NotBlank(message = "Required String parameter city is not present")
    String city) throws ApiException {
        LOG.trace("Entering getWeatherByCity(city={})", city);
        try {
            return new ResponseEntity<List<WeatherPredictionData>>(
                    weatherService.getWeather(city), HttpStatus.OK);
        } catch (ApiException ex) {
            throw new ResourceNotFoundException(Constants.WEATHER_NOT_FOUND + city);
        }
    }

}
