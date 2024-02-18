package com.rcarvalho.todo.infra;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.rcarvalho.todo.entity.dto.ExceptionDTO;
import com.rcarvalho.todo.exceptions.TodoNotFoundException;

@RestControllerAdvice
public class AdviceController {
    
    @ExceptionHandler(TodoNotFoundException.class)
    public ResponseEntity<ExceptionDTO> threatTodoNotFound(TodoNotFoundException e) {
                return new ResponseEntity<>(
                    new ExceptionDTO(e.getMessage(), 404), 
                    HttpStatus.NOT_FOUND
                );
    }
}
