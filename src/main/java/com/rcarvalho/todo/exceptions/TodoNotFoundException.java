package com.rcarvalho.todo.exceptions;

import java.util.UUID;

public class TodoNotFoundException extends NotFoundException{
    public TodoNotFoundException(UUID id) {
        super(String.format("To Do [%s] not found", id.toString()));
    }
    
}
