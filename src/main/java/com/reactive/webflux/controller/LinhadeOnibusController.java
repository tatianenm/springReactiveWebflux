package com.reactive.webflux.controller;

import com.reactive.webflux.document.LinhaDeOnibus;
import com.reactive.webflux.repository.LinhadeOnibusRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/rest/linhas")
public class LinhadeOnibusController {

    private LinhadeOnibusRepository linhadeOnibusRepository;

    public LinhadeOnibusController(LinhadeOnibusRepository linhadeOnibusRepository) {
        this.linhadeOnibusRepository = linhadeOnibusRepository;
    }

    @GetMapping
    public Flux<LinhaDeOnibus> findAll(){
       // https://www.youtube.com/watch?v=eyN9H_EA7tI
        //https://medium.com/nstech/programa%C3%A7%C3%A3o-reativa-com-spring-boot-webflux-e-mongodb-chega-de-sofrer-f92fb64517c3
return null;
    }
}
