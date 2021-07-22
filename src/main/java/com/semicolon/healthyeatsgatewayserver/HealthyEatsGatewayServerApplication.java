package com.semicolon.healthyeatsgatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class HealthyEatsGatewayServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(HealthyEatsGatewayServerApplication.class, args);
    }

}
