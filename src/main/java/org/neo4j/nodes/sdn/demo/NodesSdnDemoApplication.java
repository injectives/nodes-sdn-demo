package org.neo4j.nodes.sdn.demo;

import io.micrometer.observation.ObservationRegistry;
import org.neo4j.driver.observation.micrometer.MicrometerObservationProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.neo4j.autoconfigure.ConfigBuilderCustomizer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NodesSdnDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(NodesSdnDemoApplication.class, args);
    }

    @Bean
    ConfigBuilderCustomizer observationCustomizer(ObservationRegistry observationRegistry) {
        return configBuilder -> {
            var observationProvider = MicrometerObservationProvider.builder(observationRegistry)
                    .alwaysIncludeQuery(true)
                    .includeQueryParameters(true)
                    .includeUrlScheme(true)
                    .includeUrlTemplate(true)
                    .requestHeaderPredicate(name -> true)
                    .responseHeaderPredicate(name -> true)
                    .build();
            configBuilder.withObservationProvider(observationProvider);
        };
    }
}
