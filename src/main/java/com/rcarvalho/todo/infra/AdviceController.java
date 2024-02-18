package com.rcarvalho.todo.infra;

import java.util.InputMismatchException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.rcarvalho.todo.entity.dto.ExceptionDTO;
import com.rcarvalho.todo.exceptions.TodoNotFoundException;

@RestControllerAdvice
public class AdviceController {
    
    @ExceptionHandler(TodoNotFoundException.class)
    public ResponseEntity<ExceptionDTO> threatTodoNotFound(TodoNotFoundException e) {
                return new ResponseEntity<>(
                    new ExceptionDTO(e.getMessage(), HttpStatus.NOT_FOUND.value()), 
                    HttpStatus.NOT_FOUND
                );
    }

    @ExceptionHandler(InputMismatchException.class)
    public ResponseEntity<ExceptionDTO> threatInputMissMatch(InputMismatchException e) {
        return new ResponseEntity<>(
            new ExceptionDTO(e.getMessage(), HttpStatus.BAD_REQUEST.value()),
            HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionDTO> threatHttpMessageNotReadable(HttpMessageNotReadableException e) {
        return new ResponseEntity<>(
            new ExceptionDTO("requerid request body is missing", HttpStatus.BAD_REQUEST.value()),
            HttpStatus.BAD_REQUEST
        );
    }
}
