package com.github.mtarrr.pis.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleAllExceptions() {
        return new ResponseEntity<>(new ApiError(ErrorCode.UNEXPECTED_ERROR.getMessage(), ErrorCode.UNEXPECTED_ERROR.getCode()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
                                                                   HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(new ApiError(ex.getMessage(), ErrorCode.NO_HANDLER_FOUND.getCode()), status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        return new ResponseEntity<>(new ApiError(ErrorCode.MALFORMED_JSON_REQUEST.getMessage(), ErrorCode.MALFORMED_JSON_REQUEST.getCode()), status);
    }

    @ExceptionHandler(ElasticServiceException.class)
    protected ResponseEntity<Object> handleElasticServiceEx() {
        return new ResponseEntity<>(new ApiError(ErrorCode.ELASTIC_SERVICE_ERROR.getMessage(), ErrorCode.ELASTIC_SERVICE_ERROR.getCode()),
                HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(NotificationServiceException.class)
    protected ResponseEntity<Object> handleNotificationServiceEx() {
        return new ResponseEntity<>(new ApiError(ErrorCode.NOTIFICATION_SERVICE_ERROR.getMessage(), ErrorCode.NOTIFICATION_SERVICE_ERROR.getCode()),
                HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler({MyEntityNotFoundException.class, EntityNotFoundException.class})
    protected ResponseEntity<Object> handleEntityNotFoundEx(MyEntityNotFoundException e) {
        return new ResponseEntity<>(new ApiError(ErrorCode.ENTITY_NOT_FOUND.getMessage() + e.getMessage(), ErrorCode.ENTITY_NOT_FOUND.getCode()), HttpStatus.NOT_FOUND);
    }


}
