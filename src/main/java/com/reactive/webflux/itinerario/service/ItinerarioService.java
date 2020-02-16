package com.reactive.webflux.itinerario.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reactive.webflux.itinerario.dto.ItinerarioDTO;
import com.reactive.webflux.itinerario.repository.ItinerarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
public class ItinerarioService {

    private ItinerarioRepository itinerarioRepository;

    private static final String ENDPOINT_ITINERARIO = "http://www.poatransporte.com.br/php/facades/process.php?a=il&p=5566";


    @Autowired
    public ItinerarioService(ItinerarioRepository itinerarioRepository){
        this.itinerarioRepository = itinerarioRepository;
    }

    public Flux<ItinerarioDTO> findAll() {
        var strategies = ExchangeStrategies
                .builder()
                .codecs(clientDefaultCodecsConfigurer -> {
                    clientDefaultCodecsConfigurer.defaultCodecs()
                            .jackson2JsonEncoder(new Jackson2JsonEncoder(new ObjectMapper()
                                    .configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false),
                                    MediaType.TEXT_HTML));
                    clientDefaultCodecsConfigurer.defaultCodecs()
                            .jackson2JsonDecoder(new Jackson2JsonDecoder(new ObjectMapper()
                                    .configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false),
                                    MediaType.TEXT_HTML));
                }).build();

        var webClient = WebClient.builder().exchangeStrategies(strategies).baseUrl(ENDPOINT_ITINERARIO).build();
        return webClient.get()
                .accept(APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(ItinerarioDTO.class);
    }

    public Object streams(String codigo) {
        return null;
    }
}
