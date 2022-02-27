package com.telstra.codechallenge.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;


@Slf4j
@RestControllerAdvice
public class AllExceptionHandler {

    @ExceptionHandler({Exception.class, HttpClientErrorException.class})
    public ResponseEntity<RestApiError> handleException(Exception ex) {
        log.error("Exception caught: ", ex);
        return new ResponseEntity<>(new RestApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
