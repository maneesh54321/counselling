package com.vedantu.counselling.data.exception.handler;

import com.vedantu.counselling.data.exception.AuthenticationException;
import com.vedantu.counselling.data.exception.InvalidInputException;
import com.vedantu.counselling.data.response.Response;
import com.vedantu.counselling.data.response.ResponseStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    private final String MAX_ALLOWED_UPLOAD_SIZE_ERROR;

    public ControllerExceptionHandler(
            @Value("${spring.servlet.multipart.max-file-size}") String maxAllowedUploadSize
    ) {
        MAX_ALLOWED_UPLOAD_SIZE_ERROR = String.format(
                "The file exceeds maximum upload size of %s.",
                maxAllowedUploadSize
        );
    }

    @ExceptionHandler(value = { AuthenticationException.class })
    public ResponseEntity<Response<String>> handleAuthenticationException(
            Exception ex, WebRequest request) {
        log.error(ex.getMessage(), ex);
        Response<String> errorResponse = new Response<>(ResponseStatus.FAILED, ex.getMessage());
        return createExceptionResponse(errorResponse,
                new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = { MaxUploadSizeExceededException.class })
    public ResponseEntity<Response<String>> handleMaxUploadSizeExceededException(
            Exception ex, WebRequest request) {
        log.error(ex.getMessage(), ex);
        Response<String> errorResponse = new Response<>(ResponseStatus.FAILED, MAX_ALLOWED_UPLOAD_SIZE_ERROR);
        return createExceptionResponse(errorResponse,
                new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { InvalidInputException.class })
    public ResponseEntity<Response<String>> handleInputValidationException(
            Exception ex, WebRequest request) {
        log.error(ex.getMessage(), ex);
        Response<String> errorResponse = new Response<>(ResponseStatus.FAILED, ex.getMessage());
        return createExceptionResponse(errorResponse,
                new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<Response<String>> handleCheckedException(
            Exception ex, WebRequest request) {
        log.error(ex.getMessage(), ex);
        Response<String> errorResponse = new Response<>(ResponseStatus.FAILED, ex.getMessage());
        return createExceptionResponse(errorResponse,
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = { RuntimeException.class })
    public ResponseEntity<Response<String>> handleRuntimeException(
            Exception ex, WebRequest request) {
        log.error(ex.getMessage(), ex);
        Response<String> errorResponse = new Response<>(
                ResponseStatus.FAILED,
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()
        );
        return createExceptionResponse(errorResponse,
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private <U> ResponseEntity<U> createExceptionResponse(U body, HttpHeaders headers, HttpStatus status){
        return new ResponseEntity<>(body, headers, status);
    }
}
