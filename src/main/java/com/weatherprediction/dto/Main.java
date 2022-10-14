package com.weatherprediction.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Main {

    private float temp;
    @JsonProperty("temp_min")
    private float tempMin;
    @JsonProperty("temp_max")
    private float tempMax;
    private float pressure;
    @JsonProperty("sea_level")
    private float seaLevel;
    @JsonProperty("grnd_level")
    private float grndLevel;
    private float humidity;
    @JsonProperty("temp_kf")
    private float tempKF;

}
