package com.sample.countries.exception;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.log4j.Log4j2;
import org.openapitools.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex, WebRequest request) {
        log.error(request, ex);
        return new ResponseEntity<>(new ErrorResponse().message("There is an internal error, please try again later."), HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(value = {
            HttpClientErrorException.class,
            IllegalArgumentException.class,
            IllegalStateException.class,
            MissingServletRequestParameterException.class,
            NoResourceFoundException.class})
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(Exception ex, WebRequest request) {
        log.error(request, ex);
        return new ResponseEntity<>(new ErrorResponse().message("The requested resource is not found, please double check your request."), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException e) {
        return new ResponseEntity<>(new ErrorResponse().message("Not valid due to validation error: " + e.getConstraintViolations().iterator().next().getMessage()), HttpStatus.BAD_REQUEST);
    }


}
