package com.reactive.webflux;

import com.reactive.webflux.itinerario.ItinerarioHandler;
import com.reactive.webflux.linhadeonibus.LinhaDeOnibusHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class LinhaDeOnibusRouter {
    @Bean
    public RouterFunction<ServerResponse> route(LinhaDeOnibusHandler handler, ItinerarioHandler itinerarioHandler) {
        return RouterFunctions
                .route(RequestPredicates.GET("webflux/v1/linhas"), handler::findAll)
                .andRoute(RequestPredicates.GET("webflux/v1/linhas/{nome}"), handler::findByName)
                .andRoute(RequestPredicates.POST("webflux/v1/linhas"), handler::save)
                //.andRoute(RequestPredicates.PUT("webflux/v1/linhas")), handler::update)
                .andRoute(RequestPredicates.GET("webflux/v1/linhas/{codigo}/events"), handler::events)
                .andRoute(RequestPredicates.GET("webflux/v1/itinerarios"), itinerarioHandler::findAll)
                // .andRoute(RequestPredicates.GET("webflux/v1/itinerarios/{nome}"), itinerarioHandler::findByName)
                .andRoute(RequestPredicates.POST("webflux/v1/itinerarios"), itinerarioHandler::save)
                //.andRoute(RequestPredicates.PUT("webflux/v1/itinerarios")), itinerarioHandler::update)
                .andRoute(RequestPredicates.GET("webflux/v1/itinerarios/{codigo}/events"), itinerarioHandler::events);
    }


}
