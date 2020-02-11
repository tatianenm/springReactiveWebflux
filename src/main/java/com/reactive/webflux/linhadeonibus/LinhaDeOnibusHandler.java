package com.reactive.webflux.linhadeonibus;

import com.reactive.webflux.linhadeonibus.dto.LinhaDeOnibusDTO;
import com.reactive.webflux.linhadeonibus.model.LinhaDeOnibus;
import com.reactive.webflux.linhadeonibus.service.LinhaDeOnibusService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;


@Component
public class LinhaDeOnibusHandler {

    private LinhaDeOnibusService linhadeOnibusService;

    public LinhaDeOnibusHandler(LinhaDeOnibusService linhadeOnibusService) {
        this.linhadeOnibusService = linhadeOnibusService;
    }

    public Mono<ServerResponse> findAll(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(linhadeOnibusService.findAll(), LinhaDeOnibusDTO.class)
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> findByName(ServerRequest request) {
        String nome = request.pathVariable("nome");
        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(linhadeOnibusService.findByName(nome.toUpperCase()), LinhaDeOnibusDTO.class)
                .switchIfEmpty(ServerResponse.notFound().build());
    }

//    @Transactional
//    public Mono<ServerResponse> save(ServerRequest request) {
//        final Mono<LinhaDeOnibusDTO> linhaDeOnibusMono = request.bodyToMono(LinhaDeOnibusDTO.class);
//        return ServerResponse.ok()
//                .contentType(APPLICATION_JSON)
//                .body(BodyInserters.fromPublisher(linhaDeOnibusMono.flatMap(linhadeOnibusService::save), LinhaDeOnibus.class))
//                .switchIfEmpty(ServerResponse.badRequest().build());
//    }


    @Transactional
    public Mono<ServerResponse> update(ServerRequest request) {
        return Mono
                .zip(
                        (dados) -> {
                            var linha = (LinhaDeOnibusDTO) dados[0];
                            var linha2 = (LinhaDeOnibusDTO) dados[1];
                            linha.setCodigo(linha2.getCodigo());
                            linha.setNome(linha2.getNome());
                            return linha;
                        },
                        linhadeOnibusService.findById(request.pathVariable("id")),
                        request.bodyToMono(LinhaDeOnibus.class)
                ).cast(LinhaDeOnibusDTO.class)
                .flatMap(linhaDeOnibus -> linhadeOnibusService.update(linhaDeOnibus))
                .flatMap(linhaDeOnibus -> ServerResponse.noContent().build());
    }


}
