package com.weatherprediction.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Weather {
    private String cod;
    private float message;
    private float cnt;
    private List<WeatherObjectList> list = new ArrayList<WeatherObjectList>();
    private City city;

}
