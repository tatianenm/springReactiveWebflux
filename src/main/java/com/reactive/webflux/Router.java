package com.reactive.webflux;

import com.reactive.webflux.itinerario.ItinerarioHandler;
import com.reactive.webflux.linhadeonibus.LinhaDeOnibusHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class Router {
    @Bean
    public RouterFunction<ServerResponse> route(LinhaDeOnibusHandler handler, ItinerarioHandler itinerarioHandler) {
        return RouterFunctions
                .route(GET("webflux/v1/linhas"), handler::findAll)
                .andRoute(GET("webflux/v1/linhas/{nome}"), handler::findByName)
                .andRoute(POST("webflux/v1/linhas"), handler::save)
                .andRoute(PUT("webflux/v1/linhas"), handler::update)
                .andRoute(GET("webflux/v1/linhas/{codigo}/events"), handler::events)
                .andRoute(GET("webflux/v1/itinerarios"), itinerarioHandler::findAll)
                .andRoute(POST("webflux/v1/itinerarios"), itinerarioHandler::save)
                .andRoute(GET("webflux/v1/itinerarios/{codigo}/events"), itinerarioHandler::events);
    }


}
