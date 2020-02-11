package com.reactive.webflux.linhadeonibus.repository;

import com.reactive.webflux.linhadeonibus.model.LinhaDeOnibus;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;


public interface LinhaDeOnibusRepository extends ReactiveMongoRepository<LinhaDeOnibus, String> {
}
