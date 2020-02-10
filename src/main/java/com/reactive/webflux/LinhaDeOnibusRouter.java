package com.reactive.webflux;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class LinhaDeOnibusRouter {
    @Bean
    public RouterFunction<ServerResponse> route(LinhaDeOnibusHandler handler){
        return RouterFunctions
                .route(GET("/linhas").and(accept(MediaType.APPLICATION_JSON)), handler::findAll)
                .andRoute(GET("/linhas/{nome}").and(accept(MediaType.APPLICATION_JSON)), handler::findByName)
                .andRoute(POST("/salvar").and(accept(MediaType.APPLICATION_JSON)), handler::save)
                .andRoute(PUT("/editar").and(accept(MediaType.APPLICATION_JSON)), handler::update);
    }


}
