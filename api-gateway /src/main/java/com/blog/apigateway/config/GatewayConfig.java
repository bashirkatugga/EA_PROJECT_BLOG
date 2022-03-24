package com.blog.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration

public class GatewayConfig {

    @Resource
    AuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("USER-SERVICE", r -> r.path("/users/**", "/authenticate")
                        .filters(f -> f.filter(filter))
                        .uri("lb://USER-SERVICE"))
                .route("POST-SERVICE", r -> r.path("/posts/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://POST-SERVICE"))

                .route("COMMENT-SERVICE", r -> r.path("/comments/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://COMMENT-SERVICE"))
                .build();
    }

}
