package com.weatherprediction.util;

import com.weatherprediction.client.IWeatherClient;
import com.weatherprediction.dto.Weather;
import com.weatherprediction.dto.WeatherObjectList;
import com.weatherprediction.dto.response.WeatherPredictionData;
import com.weatherprediction.exception.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class WeatherUtil {
    private static final Logger LOG = LoggerFactory.getLogger(WeatherUtil.class);
    private int numberOfDays = 3;
    @Autowired
    private IWeatherClient weatherClient;
    @Autowired
    private StringStreamUtil stringStreamUtil;

    public List<WeatherPredictionData> getPrediction(String city) throws ApiException {

        LOG.debug("number of days is {}", numberOfDays);

        List<WeatherPredictionData> predictions = new ArrayList<>();
        Optional<Weather> weatherResponse = Optional.ofNullable(weatherClient.getWeather(city));
        if (weatherResponse.isPresent()) {
            List<WeatherObjectList> list = weatherResponse.get().getList();
            list.sort(Comparator.comparing(WeatherObjectList::getDateText).reversed());
            List<WeatherObjectList> distinctDaysPredictedWeather = list.stream()
                    .filter(stringStreamUtil.distinctByKey(p -> p.getDateText().substring(0, 10)))
                    .collect(Collectors.toList());

            List<WeatherObjectList> upcomingDaysForecasts = distinctDaysPredictedWeather.size() <= numberOfDays ? distinctDaysPredictedWeather : distinctDaysPredictedWeather.subList(0, numberOfDays);
            for (int i = 0; i < upcomingDaysForecasts.size(); i++) {
                WeatherPredictionData prediction = getPredictionData(upcomingDaysForecasts);
                prediction.setDate(upcomingDaysForecasts.get(i).getDateText());
                predictions.add(prediction);
            }
        }
        return predictions;
    }

    private WeatherPredictionData getPredictionData(List<WeatherObjectList> sublist) {
        WeatherPredictionData weatherPrediction;
        double lowTemperature = Integer.MAX_VALUE;
        double highTemperature = Integer.MIN_VALUE;
        double maxWindSpeed = Integer.MIN_VALUE;
        boolean isRain = false;
        boolean isThunderstorm = false;
        for (WeatherObjectList data : sublist) {
            if (lowTemperature > data.getMain().getTempMin()) {
                lowTemperature = data.getMain().getTempMin();
            }
            if (highTemperature < data.getMain().getTempMax()) {
                highTemperature = data.getMain().getTempMax();
            }
            if (maxWindSpeed < data.getWind().getSpeed()) {
                maxWindSpeed = data.getWind().getSpeed();
            }
            if (!isRain) {
                isRain = data.getWeather().get(0).getMain().contains(Constants.RAIN);
            }

            if (!isThunderstorm) {
                isThunderstorm = data.getWeather().get(0).getDescription().equals(Constants.THUNDERSTORMS);
            }
        }
        weatherPrediction = new WeatherPredictionData(highTemperature, lowTemperature, maxWindSpeed);
        weatherPrediction.setRain(isRain);
        weatherPrediction.setThunderstorm(isThunderstorm);
        return weatherPrediction;
    }

}
