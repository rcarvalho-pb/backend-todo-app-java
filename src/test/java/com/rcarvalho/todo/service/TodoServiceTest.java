package com.rcarvalho.todo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rcarvalho.todo.entity.Todo;
import com.rcarvalho.todo.exceptions.TodoNotFoundException;
import com.rcarvalho.todo.repository.TodoRepository;


@ExtendWith(MockitoExtension.class)
public class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoService todoService;

    private Todo todo;

    @BeforeEach
    void setup() {
        UUID id = UUID.randomUUID();
        todo = Todo.builder()
            .id(id)
            .name("Teste")
            .description("Teste")
            .done(false)
            .priority(1)
            .build();
    }

    @Test
    @DisplayName("should create Todo successfuly")
    void testCreateTodoSuccess() {
        Todo newTodo = Todo.builder()
            .name("Teste")
            .description("Teste")
            .done(false)
            .priority(1)
            .build();

        when(this.todoRepository.save(newTodo)).thenReturn(todo);
        Todo savedTodo = this.todoService.create(newTodo);
        verify(todoRepository, times(1)).save(any());
        assertTrue(savedTodo.getName() == newTodo.getName());
    }

    @Test
    void testDelete() {
        UUID id = todo.getId();
        this.todoRepository.deleteById(id);
        verify(this.todoRepository, times(1)).deleteById(any());
    }

    @Test
    void testDelete_ShouldThrowExceptionWhenIdNotExists() {
        UUID id = UUID.randomUUID();
        assertThrows(TodoNotFoundException.class, () -> this.todoService.delete(id));
    }

    @Test
    void testFindAll() {
        when(this.todoRepository.findAll()).thenReturn(List.of(todo));
        List<Todo> todos = this.todoRepository.findAll();
        verify(this.todoRepository, times(1)).findAll();
        assertIterableEquals(List.of(todo), todos);
    }

    @Test
    void testFindById() {
        UUID id = todo.getId();
        when(this.todoRepository.findById(id)).thenReturn(Optional.of(todo));
        Todo searchTodo = this.todoRepository.findById(id).orElse(null);
        verify(this.todoRepository, times(1)).findById(id);
        assertEquals(searchTodo, todo);
    }

    @Test
    void testFindById_ShouldReturnErrorWhenIdNotExists() {
        UUID id = UUID.randomUUID();
        when(this.todoRepository.findById(id)).thenThrow(TodoNotFoundException.class);
        assertThrows(TodoNotFoundException.class, () -> this.todoRepository.findById(id));
    }

    @Test
    void testUpdate() {
        UUID id = todo.getId();
        Todo updateTodo = new Todo("Teste2", "Test2", true, 10);
        when(this.todoRepository.findById(id)).thenReturn(Optional.of(todo));
        Optional<Todo> searchTodo = this.todoRepository.findById(id);
        
        verify(this.todoRepository, times(1)).findById(id);

        searchTodo.ifPresent(t -> {
            t.setName(updateTodo.getName());
            t.setDescription(updateTodo.getDescription());
            t.setDone(updateTodo.getDone());
            t.setPriority(updateTodo.getPriority());
        });

        when(this.todoRepository.save(searchTodo.get())).thenReturn(searchTodo.get());
        Todo newTodo = this.todoRepository.save(searchTodo.get());
        verify(this.todoRepository, times(1)).save(any());
        assertTrue(newTodo.getId().equals(todo.getId()));
    }
}
