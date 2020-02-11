package com.reactive.webflux.linhadeonibus.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reactive.webflux.linhadeonibus.dto.LinhaDeOnibusDTO;
import com.reactive.webflux.linhadeonibus.repository.LinhaDeOnibusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Objects;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
public class LinhaDeOnibusService {

    private LinhaDeOnibusRepository linhaDeOnibusRepository;

    private static final String ENDPOINT_LINHAS_DE_ONIBUS = "http://www.poatransporte.com.br/php/facades/process.php?a=nc&p=%&t=o";

    @Autowired
    public LinhaDeOnibusService(LinhaDeOnibusRepository linhaDeOnibusRepository) {
        this.linhaDeOnibusRepository = linhaDeOnibusRepository;
    }

    @Cacheable(value = "linhaDeOnibusDTOS")
    public Mono<LinhaDeOnibusDTO> findAll() {
        var strategies = ExchangeStrategies
                .builder()
                .codecs(clientDefaultCodecsConfigurer -> {
                    clientDefaultCodecsConfigurer.defaultCodecs().jackson2JsonEncoder(new Jackson2JsonEncoder(new ObjectMapper(), MediaType.TEXT_HTML));
                    clientDefaultCodecsConfigurer.defaultCodecs().jackson2JsonDecoder(new Jackson2JsonDecoder(new ObjectMapper(), MediaType.TEXT_HTML));
                }).build();

        var webClient = WebClient.builder().exchangeStrategies(strategies).baseUrl(ENDPOINT_LINHAS_DE_ONIBUS).build();

        return webClient.get()
                .accept(APPLICATION_JSON)
                .retrieve()
                .bodyToMono(LinhaDeOnibusDTO.class);
    }

    public Mono<LinhaDeOnibusDTO> save(LinhaDeOnibusDTO linhaDeOnibusDTO) {
        return null;
    }

    public Mono<LinhaDeOnibusDTO> findByName(String name) {
        return findAll().filter(linhaDeOnibusDTO -> Objects.equals(linhaDeOnibusDTO.getNome(), name));
    }

    public Mono<LinhaDeOnibusDTO> findById(String id) {
        return null;
    }

    public Mono<LinhaDeOnibusDTO> update(LinhaDeOnibusDTO linhaDeOnibusDTO) {
        return null;
    }

//    public Flux<Tuple2<Long, LinhaDeOnibusDTO>> getLinhaDeOnibusList() {
//        Flux<Long> interval = Flux.interval(Duration.ofSeconds(10));
//        Flux<LinhaDeOnibusDTO> playlistFlux = this.findAll();
//
//        return Flux.zip(interval, playlistFlux);
//
//    }
}