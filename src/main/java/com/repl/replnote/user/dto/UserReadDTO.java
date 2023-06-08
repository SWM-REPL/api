package com.repl.replnote.user.dto;

import lombok.Data;

@Data
public class UserReadDTO {
    private String userId;
    private String name;

    public UserReadDTO(String userId, String name) {
        this.userId = userId;
        this.name = name;
    }
}
