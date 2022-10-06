package com.weatherprediction.service;

import com.weatherprediction.dto.WeatherForecast;
import org.springframework.stereotype.Service;

import java.util.List;

public interface WeatherService {

    List<WeatherForecast> getCityWeather(String city);

}
