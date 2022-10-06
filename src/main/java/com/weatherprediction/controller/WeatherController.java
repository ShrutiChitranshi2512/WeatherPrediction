package com.weatherprediction.controller;

import com.weatherprediction.dto.WeatherForecast;
import com.weatherprediction.exception.ResourceNotFoundException;
import com.weatherprediction.service.WeatherService;
import com.weatherprediction.util.Constants;
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

@RestController()
@RequestMapping("/v1/weather")
public class WeatherController {
    private static final Logger LOG = LoggerFactory.getLogger(WeatherController.class);
    @Autowired
    private WeatherService weatherService;

    @GetMapping("/{city}")
    public ResponseEntity<List<WeatherForecast>> getWeatherByCity
            (@PathVariable String city) {
        LOG.trace("Entering getWeatherByCity(city={})", city);

        if (city == null || city.isBlank()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            return new ResponseEntity<List<WeatherForecast>>(
                    weatherService.getCityWeather(city), HttpStatus.OK);
        } catch (Exception ex) {
            throw new ResourceNotFoundException(Constants.WEATHER_NOT_FOUND + city);
        }
    }

}
