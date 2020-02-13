package com.reactive.webflux.linhadeonibus.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LinhaDeOnibus)) return false;
        LinhaDeOnibus that = (LinhaDeOnibus) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(idRemoto, that.idRemoto) &&
                Objects.equals(codigo, that.codigo) &&
                Objects.equals(nome, that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
