package com.semicolon.healthyeatsgatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;



@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class HealthyEatsGatewayServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(HealthyEatsGatewayServerApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
      return new RestTemplate();
    }

}
