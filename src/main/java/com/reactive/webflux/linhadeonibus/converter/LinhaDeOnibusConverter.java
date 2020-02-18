package com.reactive.webflux.linhadeonibus.converter;

import com.reactive.webflux.linhadeonibus.dto.LinhaDeOnibusDTO;
import com.reactive.webflux.linhadeonibus.model.LinhaDeOnibus;
import org.springframework.stereotype.Component;

@Component
public class LinhaDeOnibusConverter {

    public LinhaDeOnibus toEntity(LinhaDeOnibusDTO linhaDeOnibusDTO) {
        return LinhaDeOnibus.builder()
                .codigo(linhaDeOnibusDTO.getCodigo())
                .idRemoto(linhaDeOnibusDTO.getId())
                .nome(linhaDeOnibusDTO.getNome())
                .build();
    }

    public LinhaDeOnibusDTO toDTO(LinhaDeOnibus linhaDeOnibus) {
        return LinhaDeOnibusDTO.builder()
                .codigo(linhaDeOnibus.getCodigo())
                .id(linhaDeOnibus.getId())
                .nome(linhaDeOnibus.getNome())
                .build();
    }
}
