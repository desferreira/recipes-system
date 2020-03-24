package com.diego.recipes.repositories.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ResourceNotFoundHandler extends RuntimeException {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<StandardError> resourceNotFound(NoSuchElementException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;

        StandardError err = new StandardError(Instant.now(),
                status.value(),
                "Resource not found",
                e.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
