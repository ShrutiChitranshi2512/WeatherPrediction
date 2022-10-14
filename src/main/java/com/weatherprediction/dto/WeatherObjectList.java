package com.weatherprediction.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class WeatherObjectList{//DataList {

    private float dt;
    private Main main;
    private List<WeatherDetail> weather;
    private Clouds clouds;
    private Wind wind;
    private Sys sys;
    @JsonProperty("dt_txt")
    private String dateText;
}
