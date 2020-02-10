package com.reactive.webflux.service;

import com.reactive.webflux.dto.LinhaDeOnibusDTO;
import com.reactive.webflux.repository.LinhadeOnibusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
@RequestMapping("/linhas")
public class LinhadeOnibusService {

    private LinhadeOnibusRepository linhadeOnibusRepository;

    private static final String ENDPOINT_LINHAS_DE_ONIBUS = "http://www.poatransporte.com.br/php/facades/process.php?a=nc&p=%&t=o";


    public LinhadeOnibusService(LinhadeOnibusRepository linhadeOnibusRepository) {
        this.linhadeOnibusRepository = linhadeOnibusRepository;
    }

    @GetMapping(value = "/linhas")
    public Flux<LinhaDeOnibusDTO> findAll() {
        Flux<LinhaDeOnibusDTO> linhasDeOnibusDTO;
        return WebClient.create(ENDPOINT_LINHAS_DE_ONIBUS).get()
                .uri("/linhas").accept(APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(LinhaDeOnibusDTO.class);


    }

    public Flux<LinhaDeOnibusDTO> save(){
return null;
    }

    public Flux<LinhaDeOnibusDTO> findByName(String name){
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
