package com.reactive.webflux.linhadeonibus;

import com.reactive.webflux.linhadeonibus.dto.LinhaDeOnibusDTO;
import com.reactive.webflux.linhadeonibus.model.LinhaDeOnibus;
import com.reactive.webflux.linhadeonibus.service.LinhaDeOnibusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;


@Component
public class LinhaDeOnibusHandler {

    private LinhaDeOnibusService linhaDeOnibusService;

    @Autowired
    public LinhaDeOnibusHandler(LinhaDeOnibusService linhadeOnibusService) {
        this.linhaDeOnibusService = linhadeOnibusService;
    }

    public Mono<ServerResponse> findAll(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(linhaDeOnibusService.findAll(), LinhaDeOnibusDTO.class)
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> findByName(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(linhaDeOnibusService
                        .findByName(request.pathVariable("nome").toUpperCase()), LinhaDeOnibusDTO.class);
    }

    public Mono<ServerResponse> save(ServerRequest request) {
        final Mono<LinhaDeOnibusDTO> linhaDeOnibusMono = request.bodyToMono(LinhaDeOnibusDTO.class);
        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(linhaDeOnibusMono.flatMap(linhaDeOnibusService::save), LinhaDeOnibus.class)
                .switchIfEmpty(ServerResponse.badRequest().build());
    }

    public Mono<ServerResponse> events(ServerRequest serverRequest) {
        String codigo = serverRequest.pathVariable("codigo");
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(linhaDeOnibusService.streams(codigo), LinhaDeOnibusDTO.class)
                .doOnError(throwable -> new IllegalStateException(""));
    }
}


