package com.gabreuvcr.log.api.exception;

import com.gabreuvcr.log.domain.exception.DomainException;
import com.gabreuvcr.log.domain.exception.NotFoundException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        List<Error.Field> fields = new ArrayList<>();

        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            String name = ((FieldError)error).getField();
            String message = error.getDefaultMessage();
            fields.add(new Error.Field(name, message));
        }

        Error error = new Error();
        error.setStatus(status.value());
        error.setDateTime(OffsetDateTime.now());
        error.setTitle("One or more fields are invalid. Please fill in correctly and try again.");
        error.setFields(fields);
        return handleExceptionInternal(ex, error, headers, status, request);
    }

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<Object> handleDomain(DomainException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        Error error = new Error();
        error.setStatus(status.value());
        error.setDateTime(OffsetDateTime.now());
        error.setTitle(ex.getMessage());

        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFound(DomainException ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        Error error = new Error();
        error.setStatus(status.value());
        error.setDateTime(OffsetDateTime.now());
        error.setTitle(ex.getMessage());

        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }
}