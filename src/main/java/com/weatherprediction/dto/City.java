package com.weatherprediction.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class City {
    private float id;
    private String name;
    private Coord coord;
    private String country;

}
