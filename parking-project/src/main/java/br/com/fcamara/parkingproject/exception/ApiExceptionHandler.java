package br.com.fcamara.parkingproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.time.ZoneId;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e) {

        ApiException apiException = new ApiException(e.getMessage(), HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }
}
