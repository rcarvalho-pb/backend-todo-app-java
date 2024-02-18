package com.rcarvalho.todo.entity.dto;

import java.util.Optional;
import java.util.UUID;

public record TodoDTO(Optional<UUID> id, String name, String description, Boolean done, Integer priority) {

    
}