package com.reactive.webflux;

import com.reactive.webflux.document.LinhaDeOnibus;
import com.reactive.webflux.service.LinhadeOnibusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;


@Component
public class LinhaDeOnibusHandler {

    private LinhadeOnibusService linhadeOnibusService;

    @Autowired
    public LinhaDeOnibusHandler(LinhadeOnibusService linhadeOnibusService) {
        this.linhadeOnibusService = linhadeOnibusService;
    }

    public Mono<ServerResponse> findAll(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(linhadeOnibusService.findAll(), LinhaDeOnibus.class)
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> findByName(ServerRequest request) {
        String nome = request.pathVariable("nome");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(linhadeOnibusService.findByName(nome.toUpperCase()), LinhaDeOnibus.class)
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    @Transactional
    public Mono<ServerResponse> save(ServerRequest request) {
        final Mono<LinhaDeOnibus> linhaDeOnibusMono = request.bodyToMono(LinhaDeOnibus.class);
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(linhaDeOnibusMono.flatMap(linhadeOnibusService::s), LinhaDeOnibus.class))
                .switchIfEmpty(ServerResponse.badRequest().build());
    }


//    @Transactional
//    public Mono<ServerResponse> update(ServerRequest request) {
//        return Mono
//                .zip(
//                        (dados) -> {
//                            LinhaDeOnibus linha = (LinhaDeOnibus) dados[0];
//                            LinhaDeOnibus linha2 = (LinhaDeOnibus) dados[1];
//                            linha.setCodigo(linha2.getCodigo());
//                            linha.setNome(linha2.getNome());
//                            return linha;
//                        },
//                        linhadeOnibusService.findById(request.pathVariable("id")),
//                        request.bodyToMono(LinhaDeOnibus.class)
//                ).cast(LinhaDeOnibus.class)
//                .flatMap(linhaDeOnibus -> service.update(linhaDeOnibus))
//                .flatMap(linhaDeOnibus -> ServerResponse.noContent().build());
//    }


}
