package com.reactive.webflux.repository;

import com.reactive.webflux.document.LinhaDeOnibus;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface LinhadeOnibusRepository extends ReactiveMongoRepository<LinhaDeOnibus, String> {


}
