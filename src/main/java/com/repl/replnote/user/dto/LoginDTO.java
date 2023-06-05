package com.repl.replnote.user.dto;

import lombok.Data;

@Data
public class LoginDTO {
    private String userId;

    public LoginDTO(String userId) {
        this.userId = userId;
    }
}
