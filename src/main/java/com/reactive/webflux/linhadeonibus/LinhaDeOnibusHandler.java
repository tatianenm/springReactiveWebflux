package com.reactive.webflux.linhadeonibus;

import com.reactive.webflux.linhadeonibus.dto.LinhaDeOnibusDTO;
import com.reactive.webflux.linhadeonibus.model.LinhaDeOnibus;
import com.reactive.webflux.linhadeonibus.service.LinhaDeOnibusService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
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
        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(linhadeOnibusService
                        .findByName(request.pathVariable("nome").toUpperCase()), LinhaDeOnibusDTO.class);
    }


    public Mono<ServerResponse> save(ServerRequest request) {
        final Mono<LinhaDeOnibusDTO> linhaDeOnibusMono = request.bodyToMono(LinhaDeOnibusDTO.class);
        Mono<ServerResponse> serverResponseMono = ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(linhaDeOnibusMono.flatMap(linhadeOnibusService::save), LinhaDeOnibus.class)
                .switchIfEmpty(ServerResponse.badRequest().build());
        return serverResponseMono;
    }


//    public Mono<ServerResponse> createCustomer(ServerRequest request) {
//        return requestHandler.requireValidBody(
//                validRequestBody -> {
//                    return repository.insert(request.bodyToMono(Customer.class))
//                            .single()
//                            .flatMap(customer ->
//                                    created(
//                                            UriComponentsBuilder.fromUri(request.uri())
//                                                    .path("{id}")
//                                                    .build()
//                                                    .expand(customer.getId()).toUri()
//                                    ).body(fromObject(customer)));
//                },
//                request,
//                Customer.class);
//    }

//    public Mono<ServerResponse> update(ServerRequest request) {
//        return Mono
//                .zip(
//                        (dados) -> {
//                            var linha = (LinhaDeOnibusDTO) dados[0];
//                            var linha2 = (LinhaDeOnibusDTO) dados[1];
//                            linha.setCodigo(linha2.getCodigo());
//                            linha.setNome(linha2.getNome());
//                            return linha;
//                        },
//                        linhadeOnibusService.findByCodigo(request.pathVariable("codigo")),
//                        request.bodyToMono(LinhaDeOnibus.class)
//                ).cast(LinhaDeOnibusDTO.class)
//                .flatMap(linhaDeOnibus -> linhadeOnibusService.update(linhaDeOnibus))
//                .flatMap(linhaDeOnibus -> ServerResponse.noContent().build());
//    }


    public Mono<ServerResponse> events(ServerRequest serverRequest) {
        String codigo = serverRequest.pathVariable("codigo");
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(linhadeOnibusService.streams(codigo), LinhaDeOnibusDTO.class)
                .doOnError(throwable -> new IllegalStateException(""));
    }
}


