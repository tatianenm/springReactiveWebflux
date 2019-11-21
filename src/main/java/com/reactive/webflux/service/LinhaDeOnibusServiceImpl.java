package com.reactive.webflux.service;

import com.reactive.webflux.document.LinhaDeOnibus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class LinhaDeOnibusServiceImpl implements LinhaDeOnibusService {

    @Autowired
    LinhaDeOnibusService linhaDeOnibusService;

    @Override
    public Flux<LinhaDeOnibus> findAll() {
        return linhaDeOnibusService.findAll();
    }

    @Override
    public Mono<LinhaDeOnibus> findByName(String nome) {
        return linhaDeOnibusService.findByName(nome);
    }

    @Override
    public Mono<LinhaDeOnibus> save(LinhaDeOnibus linhaDeOnibus) {
        return linhaDeOnibusService.save(linhaDeOnibus);
    }

    @Override
    public Mono<LinhaDeOnibus> update(LinhaDeOnibus linhaDeOnibus) {
        return linhaDeOnibusService.update(linhaDeOnibus);
    }

    @Override
    public Mono<LinhaDeOnibus> deleteById(String id) {
        return linhaDeOnibusService.deleteById(id);
    }

    @Override
    public Mono<LinhaDeOnibus> findById(String id) {
        return null;
    }
}
