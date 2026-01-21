package com.example.onlineFoodOrderingSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
@AllArgsConstructor
public class ApiErrorResponse {


    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String path;

    public ApiErrorResponse(int status, String error, String path) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.error = error;
        this.path = path;
    }

}
