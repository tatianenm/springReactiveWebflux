package com.reactive.webflux.itinerario.repository;

import com.reactive.webflux.itinerario.model.Itinerario;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ItinerarioRepository extends ReactiveMongoRepository<Itinerario, String> {
}
