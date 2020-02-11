package com.reactive.webflux.linhadeonibus.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "linhaonibus" )
public class LinhaDeOnibus {

    @Id
    private String id;

    private String idRemoto;

    private String codigo;

    private String nome;
}
