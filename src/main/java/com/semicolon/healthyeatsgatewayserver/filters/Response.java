package com.semicolon.healthyeatsgatewayserver.filters;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Response {
    private boolean isSuccessful;
    private LocalDateTime timeStamp;
    private String message;

    public Response(boolean isSuccessful, String message) {
        this.isSuccessful = isSuccessful;
        this.message = message;
        this.timeStamp = LocalDateTime.now();
    }
}
