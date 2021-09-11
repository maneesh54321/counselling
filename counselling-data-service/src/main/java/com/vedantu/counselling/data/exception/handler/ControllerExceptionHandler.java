package com.vedantu.counselling.data.exception.handler;

import com.vedantu.counselling.data.exception.InvalidInputException;
import com.vedantu.counselling.data.view.Response;
import com.vedantu.counselling.data.view.ResponseStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { InvalidInputException.class })
    public ResponseEntity<Object> handleInputValidationException(
            Exception ex, WebRequest request) {
        Response<String> errorResponse = new Response<>(ResponseStatus.FAILED, ex.getMessage());
        return handleExceptionInternal(ex, errorResponse,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<Object> handleCheckedException(
            Exception ex, WebRequest request) {
        Response<String> errorResponse = new Response<>(ResponseStatus.FAILED, ex.getMessage());
        return handleExceptionInternal(ex, errorResponse,
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(value = { RuntimeException.class })
    public ResponseEntity<Object> handleRuntimeException(
            Exception ex, WebRequest request) {
        Response<String> errorResponse = new Response<>(ResponseStatus.FAILED, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        return handleExceptionInternal(ex, errorResponse,
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
