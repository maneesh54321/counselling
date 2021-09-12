package com.vedantu.counselling.data.exception.handler;

import com.vedantu.counselling.data.exception.InvalidInputException;
import com.vedantu.counselling.data.view.Response;
import com.vedantu.counselling.data.view.ResponseStatus;
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

    @ExceptionHandler(value = { MaxUploadSizeExceededException.class })
    public ResponseEntity<Object> handleMaxUploadSizeExceededException(
            Exception ex, WebRequest request) {
        log.error(ex.getMessage(), ex);
        Response<String> errorResponse = new Response<>(ResponseStatus.FAILED, MAX_ALLOWED_UPLOAD_SIZE_ERROR);
        return handleExceptionInternal(ex, errorResponse,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

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
        Response<String> errorResponse = new Response<>(
                ResponseStatus.FAILED,
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()
        );
        return handleExceptionInternal(ex, errorResponse,
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
