package com.reactive.webflux.service;

import com.reactive.webflux.document.LinhaDeOnibus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface LinhaDeOnibusService {

    Flux<LinhaDeOnibus> findAll();
    Mono<LinhaDeOnibus> findByName(String nome);
    Mono<LinhaDeOnibus> save(LinhaDeOnibus linhaDeOnibus);
    Mono<LinhaDeOnibus> update(LinhaDeOnibus linhaDeOnibus);
    void delete(LinhaDeOnibus linhaDeOnibus);
}
