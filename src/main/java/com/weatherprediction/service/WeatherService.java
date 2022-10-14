package com.weatherprediction.service;

import com.weatherprediction.exception.ApiException;
import com.weatherprediction.dto.response.WeatherPredictionData;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WeatherService {

    List<WeatherPredictionData> getWeather(String city) throws ApiException;

}
