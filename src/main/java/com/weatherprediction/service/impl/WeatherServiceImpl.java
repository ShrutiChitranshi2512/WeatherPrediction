package com.weatherprediction.service.impl;

import com.weatherprediction.client.IWeatherClient;
import com.weatherprediction.dto.Weather;
import com.weatherprediction.dto.WeatherForecast;
import com.weatherprediction.dto.WeatherObjectList;
import com.weatherprediction.exception.BadWeatherException;
import com.weatherprediction.service.WeatherService;
import com.weatherprediction.util.Constants;
import com.weatherprediction.util.WeatherUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherServiceImpl implements WeatherService {

    private static final Logger LOG = LoggerFactory.getLogger(WeatherServiceImpl.class);

    @Autowired
    private IWeatherClient weatherClient;

    @Override
    public List<WeatherForecast> getCityWeather(String city) {
        LOG.trace("Entering getCityWeather(city={})", city);

        Weather cityWeather = weatherClient.getWeather(city);

        List<WeatherForecast> dayWeatherList = new ArrayList<>();

        List<WeatherObjectList> threeDaysCityWeather = cityWeather.getList().subList(0, 3);

        for (WeatherObjectList weatherObjectList : threeDaysCityWeather) {

            WeatherForecast weatherForecast = new WeatherForecast();

            if (weatherObjectList.getMain() != null) {

                weatherForecast.setHigh(weatherObjectList.getMain().getTemp_max());
                weatherForecast.setLow(weatherObjectList.getMain().getTemp_min());

                float temperatureInCelsius = WeatherUtil.convertTempToCelsius(
                        weatherObjectList.getMain().getTemp());

                if (temperatureInCelsius > 40.0) {
                    weatherForecast.setMessage(Constants.USE_SUNSCREEN_LOTION);
                } else if (weatherObjectList.getWeather().get(0).getMain().contains(Constants.RAIN)) {
                    weatherForecast.setMessage(Constants.CARRY_UMBRELLA);
                } else {
                    weatherForecast.setMessage(weatherObjectList.getWeather().get(0).getMain());
                }

                if(weatherObjectList.getWind().getSpeed() > 10){
                    weatherForecast.setMessage(Constants.TOO_WINDY);
                }

                if(weatherObjectList.getWeather().get(0).getDescription().equals("Thunderstorm")){
                    weatherForecast.setMessage(Constants.THUNDERSTORM);
                }



            } else {
                LOG.error("Temperature data is missing for the city");
                throw new BadWeatherException(Constants.EXTERNAL_API_SENT_MALFORMED_DATA);
            }

            dayWeatherList.add(weatherForecast);
        }

        return dayWeatherList;
    }
}
