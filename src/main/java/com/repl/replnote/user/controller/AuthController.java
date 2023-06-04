package com.repl.replnote.user.controller;

import com.repl.replnote.user.entity.User;
import com.repl.replnote.user.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Auth", description = "Auth 관련 api 입니다.")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "/auth")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    @Operation(summary = "로그인 메서드", description = "로그인 메서드입니다.")
    public Boolean login(@RequestBody User user) {
        return authService.login(user);
    }
}
