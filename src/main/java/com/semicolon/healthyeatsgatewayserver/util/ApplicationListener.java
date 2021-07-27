package com.semicolon.healthyeatsgatewayserver.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class ApplicationListener implements org.springframework.context.ApplicationListener<ApplicationReadyEvent> {
    private final String eurekaServerUrl = "https://healthyeats-eurekaserver-dev.herokuapp.com/";
    private final String mealServiceUrl = "https://healthyeats-mealservice-dev.herokuapp.com/";
    private final String userServiceUrl = "https://healthyeats-userservice-dev.herokuapp.com/";
    private final String configServerUrl = "https://healthyeats-configserver-dev.herokuapp.com/";
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        log.info("Application Ready ......");
        restTemplate.getForEntity(configServerUrl, String.class);
        log.info("Pinged config Server ...");
        restTemplate.getForEntity(eurekaServerUrl, String.class);
        log.info("Pinged eureka server ....");
        restTemplate.getForEntity(mealServiceUrl, String.class);
        log.info("Pinged meal service ...");
        restTemplate.getForEntity(userServiceUrl, String.class);
        log.info("Pinged user service ....");
    }
}
