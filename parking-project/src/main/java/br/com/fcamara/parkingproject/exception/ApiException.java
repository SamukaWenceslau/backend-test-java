package br.com.fcamara.parkingproject.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
public class ApiException {
    private final String message;
    private final HttpStatus httpStatus;
    private final LocalDateTime timestamp;

    public ApiException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.timestamp = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
    }
}
