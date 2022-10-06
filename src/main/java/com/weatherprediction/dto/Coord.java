package com.weatherprediction.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Coord {

    private float lat;
    private float lon;

}
