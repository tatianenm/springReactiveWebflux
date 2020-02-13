package com.reactive.webflux.linhadeonibus;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class LinhaDeOnibusRouter {
    @Bean
    public RouterFunction<ServerResponse> route(LinhaDeOnibusHandler handler) {
        return RouterFunctions
                .route(RequestPredicates.GET("webflux/v1/linhas"), handler::findAll)
                .andRoute(RequestPredicates.GET("webflux/v1/linhas/{nome}"), handler::findByName)
                .andRoute(RequestPredicates.POST("webflux/v1/salvar"), handler::save)
                //.andRoute(RequestPredicates.PUT("webflux/v1/editar").and(accept(MediaType.APPLICATION_JSON)), handler::update)
                .andRoute(RequestPredicates.GET("webflux/v1/linhas/{codigo}/events"), handler::events);
    }


}
