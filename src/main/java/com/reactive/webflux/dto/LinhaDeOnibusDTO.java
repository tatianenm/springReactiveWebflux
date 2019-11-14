package com.reactive.webflux.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LinhaDeOnibusDTO {

    private String id;

    private String codigo;

    private String nome;
}
