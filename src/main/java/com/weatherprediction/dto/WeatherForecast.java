package com.weatherprediction.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class WeatherForecast    {

    private float high;
    private float low;
    private String message;
}
