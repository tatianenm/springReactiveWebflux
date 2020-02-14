package com.reactive.webflux.linhadeonibus.converter;

import com.reactive.webflux.linhadeonibus.dto.LinhaDeOnibusDTO;
import com.reactive.webflux.linhadeonibus.model.LinhaDeOnibus;

public class LinhaDeOnibusConverter {

    public LinhaDeOnibus converteParaLinhaDeOnibus(LinhaDeOnibusDTO linhaDeOnibusDTO) {
        return LinhaDeOnibus.builder()
                .codigo(linhaDeOnibusDTO.getCodigo())
                .idRemoto(linhaDeOnibusDTO.getId())
                .nome(linhaDeOnibusDTO.getNome())
                .build();
    }

    public LinhaDeOnibusDTO converteParaLinhaDeOnibusDTO(LinhaDeOnibus linhaDeOnibus) {
        return LinhaDeOnibusDTO.builder()
                .codigo(linhaDeOnibus.getCodigo())
                .id(linhaDeOnibus.getId())
                .nome(linhaDeOnibus.getNome())
                .build();
    }
}
