package com.reactive.webflux.linhadeonibus.service;

import com.reactive.webflux.linhadeonibus.converter.LinhaDeOnibusConverter;
import com.reactive.webflux.linhadeonibus.dto.LinhaDeOnibusDTO;
import com.reactive.webflux.linhadeonibus.events.LinhaDeOnibusEvents;
import com.reactive.webflux.linhadeonibus.model.LinhaDeOnibus;
import com.reactive.webflux.linhadeonibus.repository.LinhaDeOnibusRepository;
import com.reactive.webflux.util.ExchangeStrategiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.Date;
import java.util.Objects;
import java.util.stream.Stream;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static reactor.core.publisher.Flux.error;

@Service
public class LinhaDeOnibusService {

    private LinhaDeOnibusRepository linhaDeOnibusRepository;

    private LinhaDeOnibusConverter linhaDeOnibusConverter;

    private static final String ENDPOINT_LINHAS_DE_ONIBUS = "http://www.poatransporte.com.br/php/facades/process.php?a=nc&p=%&t=o";

    @Autowired
    public LinhaDeOnibusService(LinhaDeOnibusRepository linhaDeOnibusRepository, LinhaDeOnibusConverter linhaDeOnibusConverter) {
        this.linhaDeOnibusRepository = linhaDeOnibusRepository;
        this.linhaDeOnibusConverter = linhaDeOnibusConverter;
    }

    @Cacheable(value = "linhaDeOnibusDTOS")
    public Flux<LinhaDeOnibusDTO> findAll() {
        var webClient = ExchangeStrategiesUtil.estrategies(ENDPOINT_LINHAS_DE_ONIBUS);

        return webClient.get()
                .accept(APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(LinhaDeOnibusDTO.class);
    }

    @CacheEvict(value = "linhaDeOnibusDTOS", allEntries = true)
    public Mono<LinhaDeOnibusDTO> save(LinhaDeOnibusDTO linhaDeOnibusDTO) {
        if (Objects.nonNull(findAll())) {
            findAll().toStream().forEach(linha -> {
                linhaDeOnibusRepository
                        .save(linhaDeOnibusConverter.toEntity(linha));
            });
        }
        var linha = linhaDeOnibusRepository.save(linhaDeOnibusConverter.toEntity(linhaDeOnibusDTO));
        
        return linhaDeOnibusConverter.toDTO(linha);
    }

    public Flux<LinhaDeOnibusDTO> findByName(String name) {
        return findAll().filter(linhaDeOnibusDTO -> Objects.equals(linhaDeOnibusDTO.getNome().toUpperCase(), name))
                .switchIfEmpty(error(new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    public Flux<LinhaDeOnibusDTO> findByCodigo(String codigo) {
        return findAll().filter(linhaDeOnibusDTO -> linhaDeOnibusDTO.getCodigo().equalsIgnoreCase(codigo))
                .switchIfEmpty(error(new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @CacheEvict(value = "linhaDeOnibusDTOS", allEntries = true)
    public Mono<LinhaDeOnibusDTO> update(LinhaDeOnibusDTO linhaDeOnibusDTO) {
        return null;
    }

    public Flux<LinhaDeOnibusEvents> streams(String codigo) {
        return findAll().flatMap(linhaOnibus -> {
            Flux<Long> interval = Flux.interval(Duration.ofSeconds(1));
            Flux<LinhaDeOnibusEvents> events = Flux.fromStream(
                    Stream.generate(() -> new LinhaDeOnibusEvents(linhaOnibus, new Date())));
            return Flux.zip(interval, events).map(Tuple2::getT2);
        });
    }
}

