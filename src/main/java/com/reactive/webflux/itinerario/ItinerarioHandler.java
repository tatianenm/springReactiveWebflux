package com.reactive.webflux.itinerario;

import com.reactive.webflux.itinerario.dto.ItinerarioDTO;
import com.reactive.webflux.itinerario.service.ItinerarioService;
import com.reactive.webflux.linhadeonibus.dto.LinhaDeOnibusDTO;
import com.reactive.webflux.linhadeonibus.service.LinhaDeOnibusService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;


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

//    public Mono<ServerResponse> findByName(ServerRequest request) {
//        return ServerResponse.ok()
//                .contentType(APPLICATION_JSON)
//                .body(linhadeOnibusService
//                        .findByName(request.pathVariable("nome").toUpperCase()), LinhaDeOnibusDTO.class);
//    }


    public Mono<ServerResponse> save(ServerRequest request) {
        final Mono<LinhaDeOnibusDTO> linhaDeOnibusMono = request.bodyToMono(LinhaDeOnibusDTO.class);
//        Mono<ServerResponse> serverResponseMono = ServerResponse.ok()
//                .contentType(APPLICATION_JSON)
//                .body(linhaDeOnibusMono.flatMap(linhadeOnibusService::save), LinhaDeOnibus.class)
//                .switchIfEmpty(ServerResponse.badRequest().build());
        return null;//serverResponseMono;
    }




    public Mono<ServerResponse> events(ServerRequest serverRequest) {
        String codigo = serverRequest.pathVariable("codigo");
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(itinerarioService.streams(codigo), LinhaDeOnibusDTO.class)
                .doOnError(throwable -> new IllegalStateException(""));
    }
}


