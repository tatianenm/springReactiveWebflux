package com.reactive.webflux.linhadeonibus.dto;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LinhaDeOnibusDTO {

    private String id;

    private String codigo;

    private String nome;
}

