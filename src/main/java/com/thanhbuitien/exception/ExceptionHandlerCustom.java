package com.thanhbuitien.exception;

import com.thanhbuitien.response.ResponseFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;

@ControllerAdvice
public class ExceptionHandlerCustom extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InternalServerException.class)
    protected ResponseEntity<Object> handleInternalException(Exception ex) {
        return new ResponseEntity<>(ResponseFactory.getServerErrorResponse(ex.getMessage(),
                Collections.emptyMap()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ClientErrorException.class)
    protected ResponseEntity<Object> handleClientException(Exception ex) {
        return new ResponseEntity<>(ResponseFactory.getClientErrorResponse(ex.getMessage(),
                Collections.emptyMap()), HttpStatus.BAD_REQUEST);
    }
}
