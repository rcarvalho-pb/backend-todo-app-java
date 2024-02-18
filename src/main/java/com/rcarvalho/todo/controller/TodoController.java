package com.rcarvalho.todo.controller;

import java.util.List;
import java.util.UUID;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rcarvalho.todo.entity.Todo;
import com.rcarvalho.todo.entity.dto.TodoDTO;
import com.rcarvalho.todo.service.TodoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "api/v1/todos", produces = {"application/json"})
@RequiredArgsConstructor
@Tag(name = "ToDo Cotroller application")
public class TodoController {
    
    private final TodoService todoService;

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(summary = "Create a new To Do activity", method = "POST")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "To do activity created successfully"),
        @ApiResponse(responseCode = "400", description = "Empty body in requisition")
    })
    public ResponseEntity<TodoDTO> createTodo(@RequestBody final TodoDTO dto) {
        return new ResponseEntity<>(Todo.toDTO(this.todoService.create(new Todo(dto))), HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Find all To do Activities")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiResponse(responseCode = "200", description = "All To do activities")
    public ResponseEntity<List<TodoDTO>> findAll() {
        return new ResponseEntity<>(
            this.todoService.findAll().stream().map(Todo::toDTO).toList(), 
            HttpStatus.OK
        );
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Find an Activity by id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found to do activity by id"),
        @ApiResponse(responseCode = "404", description = "To do activity not found")
    })
    public ResponseEntity<TodoDTO> findById(@PathVariable("id") final UUID id) {
        return new ResponseEntity<>(
            Todo.toDTO(this.todoService.findById(id)),
            HttpStatus.OK
        );
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Update an existing To do activity")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Updated to do activity"),
        @ApiResponse(responseCode = "404", description = "To do activity not found")
    })
    public ResponseEntity<TodoDTO> update(@PathVariable("id") final UUID id, @RequestBody TodoDTO dto) {
        return new ResponseEntity<>(
            Todo.toDTO(this.todoService.update(id, new Todo(dto))),
            HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete an existing to do activity")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Updated to do activity"),
        @ApiResponse(responseCode = "404", description = "To do activity not found")
    })
    public ResponseEntity<Void> deleteById(@PathVariable("id") final UUID id) {
        this.todoService.delete(id);
        return ResponseEntity.ok().build();
    }
}
