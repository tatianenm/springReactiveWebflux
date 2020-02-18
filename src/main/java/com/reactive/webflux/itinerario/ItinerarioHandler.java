package com.reactive.webflux.itinerario;

import com.reactive.webflux.itinerario.dto.ItinerarioDTO;
import com.reactive.webflux.itinerario.service.ItinerarioService;
import com.reactive.webflux.linhadeonibus.dto.LinhaDeOnibusDTO;
import com.reactive.webflux.linhadeonibus.model.LinhaDeOnibus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.TEXT_EVENT_STREAM;


@Component
public class ItinerarioHandler {

    private ItinerarioService itinerarioService;

    public ItinerarioHandler(ItinerarioService itinerarioService) {
        this.itinerarioService = itinerarioService;
    }

    public Mono<ServerResponse> findAll(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(itinerarioService.findAll(), ItinerarioDTO.class)
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> save(ServerRequest request) {
        Mono<LinhaDeOnibusDTO> linhaDeOnibusMono = request.bodyToMono(LinhaDeOnibusDTO.class);
        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(linhaDeOnibusMono.flatMap(itinerarioService::save), LinhaDeOnibus.class)
                .switchIfEmpty(ServerResponse.badRequest().build());

    }

    public Mono<ServerResponse> events(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(TEXT_EVENT_STREAM)
                .body(itinerarioService.streams(serverRequest.pathVariable("codigo")), LinhaDeOnibusDTO.class)
                .doOnError(throwable -> new IllegalStateException(""));
    }
}


