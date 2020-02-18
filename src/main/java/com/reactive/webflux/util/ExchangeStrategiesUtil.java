package com.reactive.webflux.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

public class ExchangeStrategiesUtil {

    public static WebClient estrategies(String endpoint) {
        var strategies = ExchangeStrategies
                .builder()
                .codecs(clientDefaultCodecsConfigurer -> {
                    clientDefaultCodecsConfigurer.defaultCodecs()
                            .jackson2JsonEncoder(new Jackson2JsonEncoder(new ObjectMapper()
                                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false), MediaType.TEXT_HTML));
                    clientDefaultCodecsConfigurer.defaultCodecs()
                            .jackson2JsonDecoder(new Jackson2JsonDecoder(new ObjectMapper()
                                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false), MediaType.TEXT_HTML));
                }).build();

        return WebClient.builder().exchangeStrategies(strategies).baseUrl(endpoint).build();
    }
}
