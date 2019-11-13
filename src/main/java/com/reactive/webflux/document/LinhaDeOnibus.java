package com.reactive.webflux.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class LinhaDeOnibus {

    @Id
    private Integer id;

    private Integer idRemoto;

    private String codigo;
}
