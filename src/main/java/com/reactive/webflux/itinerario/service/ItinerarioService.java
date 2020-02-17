package com.reactive.webflux.itinerario.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reactive.webflux.itinerario.repository.ItinerarioRepository;
import com.reactive.webflux.linhadeonibus.dto.LinhaDeOnibusDTO;
import com.reactive.webflux.linhadeonibus.service.LinhaDeOnibusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
public class ItinerarioService {

    private ItinerarioRepository itinerarioRepository;

    private LinhaDeOnibusService linhaDeOnibusService;

    private static final String ENDPOINT_ITINERARIO = "http://www.poatransporte.com.br/php/facades/process.php?a=il&p=";


    @Autowired
    public ItinerarioService(ItinerarioRepository itinerarioRepository, LinhaDeOnibusService linhaDeOnibusService) {
        this.itinerarioRepository = itinerarioRepository;
        this.linhaDeOnibusService = linhaDeOnibusService;
    }

    public Flux<Tuple2<Long, Map>> findAll() {
        Flux<Long> interval = Flux.interval(Duration.ofSeconds(10));
        Flux<Map> mapFluxItinerarios =
                Flux.merge(linhaDeOnibusService.findAll().flatMap(linha-> findItinerarios(linha.getCodigo())));
       return Flux.zip(interval, mapFluxItinerarios );
    }
//    Flux<Long> interval = Flux.interval(Duration.ofSeconds(10));
//    Flux<Playlist> playlistFlux = service.findAll();
//
//        return Flux.zip(interval, playlistFlux);


    private Flux<Map> findItinerarios(String codigo) {
        var strategies = ExchangeStrategies
                .builder()
                .codecs(clientDefaultCodecsConfigurer -> {
                    clientDefaultCodecsConfigurer.defaultCodecs()
                            .jackson2JsonEncoder(new Jackson2JsonEncoder(new ObjectMapper()
                                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false),
                                    MediaType.TEXT_HTML));
                    clientDefaultCodecsConfigurer.defaultCodecs()
                            .jackson2JsonDecoder(new Jackson2JsonDecoder(new ObjectMapper()
                                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false),
                                    MediaType.TEXT_HTML));
                }).build();
        Flux<LinhaDeOnibusDTO> linhaDeOnibusDTOS = linhaDeOnibusService.findAll();

        var webClient = WebClient.builder().exchangeStrategies(strategies).baseUrl(ENDPOINT_ITINERARIO+codigo).build();
        return webClient.get()
                .accept(APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Map.class);
    }

    public Object streams(String codigo) {
        return null;
    }
}
