package com.reactive.webflux.controller;

import com.reactive.webflux.dto.LinhaDeOnibusDTO;
import com.reactive.webflux.repository.LinhadeOnibusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilderFactory;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/linhas")
public class LinhadeOnibusController {

    private LinhadeOnibusRepository linhadeOnibusRepository;

    private static final String ENDPOINT_LINHAS_DE_ONIBUS = "http://www.poatransporte.com.br/php/facades/process.php?a=nc&p=%&t=o";

    @Autowired
    private WebClient.Builder webClientBuilder;

    public LinhadeOnibusController(LinhadeOnibusRepository linhadeOnibusRepository) {
        this.linhadeOnibusRepository = linhadeOnibusRepository;
    }

    @GetMapping(value = "/linhas")
    public Flux<LinhaDeOnibusDTO> findAll() {
        Flux<LinhaDeOnibusDTO> linhasDeOnibusDTO ;

//        return linhasDeOnibusDTO
//                          .flatMap(dto -> {
//                              webClientBuilder.build()
//                                      .get()
//                                      .uri(ENDPOINT_LINHAS_DE_ONIBUS)
//                                      .retrieve()
//                                      .bodyToMono(LinhaDeOnibusDTO.class)
//                                      .block();
//                              return new LinhaDeOnibusDTO(dto.getId(), dto.getCodigo(), dto.getNome());
//                          })
//                 .collect(Collectors.toList());
        return null;
    }

//    @GetMapping(value="/playlist/webflux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//    public Flux<Tuple2<Long, Playlist>> getPlaylistByWebflux(){
//        Flux<Long> interval = Flux.interval(Duration.ofSeconds(10));
//        Flux<Playlist> playlistFlux = service.findAll();
//
//        return Flux.zip(interval, playlistFlux);
//
//    }
}
