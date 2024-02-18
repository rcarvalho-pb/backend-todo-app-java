package com.rcarvalho.todo.entity;

import java.util.Optional;
import java.util.UUID;

import com.rcarvalho.todo.entity.dto.TodoDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "todos")
@Table(name = "todos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString
@Builder
public class Todo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(name = "To do ID", example = "1b48a989-c94d-4b15-809c-7424b540fd80")
    private UUID id;
    @Schema(name = "To do name", example = "Test", required = true)
    private String name;
    @Schema(name = "To do description", example = "Test", required = true)
    private String description;
    @Schema(name = "To do done status", example = "false", required = true)
    private Boolean done;
    @Schema(name = "To do priority", example = "1", required = true)
    private Integer priority;

    public Todo(String name, String description, Boolean done, Integer priority) {
        this.name = name;
        this.description = description;
        this.done = done;
        this.priority = priority;
    }

    public Todo(TodoDTO dto) {
        dto.id().ifPresent(id -> this.id = id);
        this.name = dto.name();
        this.description = dto.description();
        this.done = dto.done();
        this.priority = dto.priority();
    }

    public static TodoDTO toDTO(Todo todo) {
        return new TodoDTO(Optional.ofNullable(todo.getId()), todo.getName(), todo.description, todo.getDone(), todo.getPriority());
    }
}
