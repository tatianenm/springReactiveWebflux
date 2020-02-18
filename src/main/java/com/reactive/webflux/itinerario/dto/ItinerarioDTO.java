package com.reactive.webflux.itinerario.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItinerarioDTO {

    private String idlinha;

    private String codigo;

    private String nome;

}
