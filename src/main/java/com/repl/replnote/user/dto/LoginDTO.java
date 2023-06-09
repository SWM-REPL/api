package com.repl.replnote.user.dto;

import lombok.Data;

@Data
public class LoginDTO {
    private String userId;
    private String name;

    public LoginDTO(String userId, String name) {
        this.userId = userId;
        this.name = name;
    }
}
