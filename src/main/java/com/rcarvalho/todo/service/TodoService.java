package com.rcarvalho.todo.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.rcarvalho.todo.entity.Todo;
import com.rcarvalho.todo.exceptions.TodoNotFoundException;
import com.rcarvalho.todo.repository.TodoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TodoService {
    
    private final TodoRepository todoRepository;
    Sort sort = Sort.by("priority").ascending();
    

    public Todo create(Todo todo) {
        return this.todoRepository.save(todo);
    }

    public List<Todo> findAll() {
        return this.todoRepository.findAll(sort);
    }

    public Todo findById(UUID id) {
        return this.todoRepository.findById(id).orElseThrow(() -> new TodoNotFoundException(id));
    }

    public Todo update(UUID id, Todo todo) {
        Optional<Todo> searchedTodo = this.todoRepository.findById(id);
        if (searchedTodo.isEmpty()) {
            throw new TodoNotFoundException(id);
        }
        return this.todoRepository.save(todo);
    }

    public void delete(UUID id) {
        this.todoRepository.deleteById(id);
    }
}
