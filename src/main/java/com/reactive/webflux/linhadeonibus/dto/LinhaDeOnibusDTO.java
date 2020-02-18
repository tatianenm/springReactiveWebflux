package com.reactive.webflux.linhadeonibus.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Objects;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LinhaDeOnibusDTO {

    private String id;

    private String codigo;

    private String nome;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LinhaDeOnibusDTO)) return false;
        LinhaDeOnibusDTO that = (LinhaDeOnibusDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(codigo, that.codigo) &&
                Objects.equals(nome, that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

