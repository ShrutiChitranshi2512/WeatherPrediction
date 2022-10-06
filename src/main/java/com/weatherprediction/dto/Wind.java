package com.weatherprediction.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Wind {

    private float speed;
    private float degree;

}
