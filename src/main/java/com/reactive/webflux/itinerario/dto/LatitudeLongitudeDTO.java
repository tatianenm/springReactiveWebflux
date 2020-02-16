package com.reactive.webflux.itinerario.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class LatitudeLongitudeDTO {

    private String type = "Point";
    private Double lat;
    private Double lng;
}

