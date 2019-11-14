package com.reactive.webflux;

import com.reactive.webflux.document.LinhaDeOnibus;
import com.reactive.webflux.service.LinhaDeOnibusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class LinhaDeOnibusHandler {

    @Autowired
    LinhaDeOnibusService service;

    public Mono<ServerResponse> findAll(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.findAll(), LinhaDeOnibus.class);
    }

    public Mono<ServerResponse> findById(ServerRequest request) {
        String nome = request.pathVariable("nome");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.findByName(nome.toUpperCase()), LinhaDeOnibus.class);
    }

    public Mono<ServerResponse> save(ServerRequest request) {
        final Mono<LinhaDeOnibus> linhaDeOnibusMono = request.bodyToMono(LinhaDeOnibus.class);
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(linhaDeOnibusMono.
                        flatMap(service::save), LinhaDeOnibus.class));
    }

    public Mono<ServerResponse> delete(ServerRequest request){
return ServerResponse.noContent()
        .build(service.delete(request.pathVariable("id")))
       // https://www.programcreek.com/java-api-examples/index.php?api=org.springframework.web.reactive.function.server.ServerResponse

    }
}
