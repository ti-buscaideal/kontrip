package com.example.kontrip.util;

import com.example.kontrip.dto.ApiResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(ApiException.class)
    private ResponseEntity<ApiResponse> handleApiException(ApiException ex) {
        ApiResponse apiResponse = buildApiResponse(ex.getMessage());
        return ResponseEntity.internalServerError().body(apiResponse);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    private ResponseEntity<ApiResponse> handleException(Exception ex) {
        ApiResponse apiResponse = buildApiResponse(ex.getMessage());
        return ResponseEntity.internalServerError().body(apiResponse);
    }

    private ApiResponse buildApiResponse(String message) {
        String data = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        return new ApiResponse(false, message, data, null);
    }
}
