package com.weatherprediction.service.impl;

import com.weatherprediction.exception.ApiException;
import com.weatherprediction.exception.ValidationException;
import com.weatherprediction.service.WeatherService;
import com.weatherprediction.dto.response.WeatherPredictionData;
import com.weatherprediction.util.WeatherUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherServiceImpl implements WeatherService {

    private static final Logger LOG = LoggerFactory.getLogger(WeatherServiceImpl.class);

    @Autowired
    private WeatherUtil weatherUtil;

    @Cacheable(value = "weatherData", key = "#city")
    @Override
    public List<WeatherPredictionData> getWeather(String city) throws ApiException {
        LOG.trace("Entering getCityWeather(city={})", city);
        if (StringUtils.isEmpty(city)||StringUtils.isWhitespace(city) ) {
            throw new ValidationException("Value of city should not be blank");
        }
        return weatherUtil.getPrediction(city);
    }
}
