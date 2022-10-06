package com.weatherprediction.client;

import org.springframework.cloud.openfeign.FeignClient;
import com.weatherprediction.dto.Weather;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "openWeatherDataClient", url = "${feign.client.get.url}")
public interface IWeatherClient {
    @RequestMapping(method = RequestMethod.GET)
    Weather getWeather(@RequestParam String q);
}
