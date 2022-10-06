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
public class WeatherObjectList {

    private float dt;
    private Main main;
    private List<WeatherDetail> weather = new ArrayList<WeatherDetail>();
    private Clouds clouds;
    private Wind wind;
    private Sys sys;
    private String dt_txt;
}
