package com.reactive.webflux.itinerario.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class Itinerario {

    private String id;

    private String codigo;

}
