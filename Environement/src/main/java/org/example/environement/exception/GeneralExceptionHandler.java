package org.example.environement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class GeneralExceptionHandler {
    @ExceptionHandler(value={NotFoundException.class})
    public ResponseEntity<String> handleNotFoundException(Exception ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity not found");
    }

    @ExceptionHandler(value={Exception.class})
    public ResponseEntity<String> handleException(NoResourceFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Route incorrecte");
    }
}
