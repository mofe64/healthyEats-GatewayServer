package com.semicolon.healthyeatsgatewayserver.filters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@RefreshScope
@Configuration
public class GatewayConfig {
    @Autowired
    AuthenticationFilter authenticationFilter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("mealservice", p -> p
                        .path("/meals/**")
                        .filters(f -> f
                                .filter(authenticationFilter)
                                .stripPrefix(1))
                        .uri("lb://HEALTHYEATS-MEALSERVICE")
                )
                .route("userservice", p -> p
                        .path("/users/**")
                        .filters(f -> f
                                .filter(authenticationFilter)
                                .stripPrefix(1))
                        .uri("lb://HEALTHYEATS-USERSERVICE")
                ).build();
    }
}
