package com.rcarvalho.todo.entity;

import java.util.UUID;

import com.rcarvalho.todo.entity.dto.TodoDTO;

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

@Entity
@Table(name = "todo")
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
    private UUID id;
    private String name;
    private String description;
    private Boolean done;
    private Integer priority;

    public Todo(TodoDTO dto) {
        this.name = dto.name();
        this.description = dto.description();
        this.done = dto.done();
        this.priority = dto.priority();
    }

    public static TodoDTO toDTO(Todo todo) {
        return new TodoDTO(todo.getName(), todo.description, todo.getDone(), todo.getPriority());
    }
}
