package com.rcarvalho.todo.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rcarvalho.todo.entity.Todo;
import com.rcarvalho.todo.entity.dto.TodoDTO;
import com.rcarvalho.todo.service.TodoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "api/v1/todos")
@RequiredArgsConstructor
public class TodoController {
    
    private final TodoService todoService;

    @PostMapping("/create")
    public ResponseEntity<TodoDTO> createTodo(@RequestBody final TodoDTO dto) {
        return new ResponseEntity<>(Todo.toDTO(this.todoService.create(new Todo(dto))), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TodoDTO>> findAll() {
        return new ResponseEntity<>(
            this.todoService.findAll().stream().map(Todo::toDTO).toList(), 
            HttpStatus.OK
        );
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TodoDTO> findById(@PathVariable("id") final UUID id) {
        return new ResponseEntity<>(
            Todo.toDTO(this.todoService.findById(id)),
            HttpStatus.OK
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoDTO> update(@PathVariable("id") final UUID id, @RequestBody TodoDTO dto) {
        return new ResponseEntity<>(
            Todo.toDTO(this.todoService.update(id, new Todo(dto))),
            HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") final UUID id) {
        this.todoService.delete(id);
        return ResponseEntity.ok().build();
    }
}
