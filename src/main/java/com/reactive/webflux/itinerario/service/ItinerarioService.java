package com.reactive.webflux.itinerario.service;

import com.reactive.webflux.itinerario.repository.ItinerarioRepository;
import com.reactive.webflux.linhadeonibus.service.LinhaDeOnibusService;
import com.reactive.webflux.util.ExchangeStrategiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.Map;

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
        var mapFluxItinerarios = Flux.merge(linhaDeOnibusService.findAll()
                .flatMap(linha -> findItinerarios(linha.getCodigo())));
        return Flux.zip(interval, mapFluxItinerarios);
    }

    private Flux<Map> findItinerarios(String codigo) {

        var webClient = ExchangeStrategiesUtil.estrategies(ENDPOINT_ITINERARIO + codigo);
        return webClient.get()
                .accept(APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Map.class);
    }

    public Object streams(String codigo) {
        return null;
    }
}
