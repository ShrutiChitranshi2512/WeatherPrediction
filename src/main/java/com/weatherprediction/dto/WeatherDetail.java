package com.weatherprediction.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class WeatherDetail {

    private float id;
    private String main;
    private String description;
    private String icon;
}
