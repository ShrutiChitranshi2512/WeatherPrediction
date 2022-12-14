package com.weatherprediction;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Weather API", description = "Rest API to get weather forecast for a city from OpenWeatherMap api", version = "v1"))
@EnableFeignClients(basePackages = {"com.weatherprediction.client"})
public class WeatherPredictionApplication {

    public static void main(String[] args) {

        SpringApplication.run(WeatherPredictionApplication.class, args);
        System.out.println("Welcome to Publicis Sapient Weather Api");
    }

}
