package com.repl.replnote.user.controller;

import com.repl.replnote.user.dao.LoginDAO;
import com.repl.replnote.user.entity.User;
import com.repl.replnote.user.service.AuthService;
import com.repl.replnote.util.Message;
import com.repl.replnote.util.StatusEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;

@RestController
@Tag(name = "Auth", description = "Auth 관련 api 입니다.")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "/auth")
    @Operation(summary = "로그인 메서드", description = "로그인 메서드입니다.")
    public ResponseEntity<Message> login(@RequestBody LoginDAO loginDAO) {
        authService.login(loginDAO);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        Message message = new Message(StatusEnum.OK, "로그인 성공!", loginDAO.getUserId());
        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }
}
