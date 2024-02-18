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
    

    public Todo create(final Todo todo) {
        return this.todoRepository.save(todo);
    }

    public List<Todo> findAll() {
        return this.todoRepository.findAll(sort);
    }

    public Todo findById(final UUID id) {
        return this.todoRepository.findById(id).orElseThrow(() -> new TodoNotFoundException(id));
    }

    public Todo update(final UUID id, final Todo todo) {
        Optional<Todo> searchedTodo = this.todoRepository.findById(id);
        if (searchedTodo.isEmpty()) {
            throw new TodoNotFoundException(id);
        }
        searchedTodo.ifPresent(t -> {
            t.setName(todo.getName());
            t.setDescription(todo.getDescription());
            t.setDone(todo.getDone());
            t.setPriority(todo.getPriority());
        });
        return this.todoRepository.save(searchedTodo.get());
    }

    public void delete(final UUID id) {
        this.todoRepository.deleteById(id);
    }
}
