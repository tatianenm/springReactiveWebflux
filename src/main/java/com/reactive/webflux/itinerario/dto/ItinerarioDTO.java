package com.reactive.webflux.itinerario.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItinerarioDTO {

    private String idlinha;

    private String codigo;

    private String nome;
private List<LatitudeLongitudeDTO> lista;


    //private List<Double> coordinates;




}
