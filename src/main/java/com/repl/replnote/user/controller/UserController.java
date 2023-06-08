package com.repl.replnote.user.controller;

import com.repl.replnote.user.dto.UserReadDTO;
import com.repl.replnote.user.entity.User;
import com.repl.replnote.user.service.UserService;
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
import java.util.Optional;

@RestController
@Tag(name = "User", description = "User 관련 api 입니다.")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/user", produces = "application/json;charset=UTF-8")
    @Operation(summary = "회원가입 메서드", description = "회원가입 메서드입니다.")
    public ResponseEntity<Message> create(@RequestBody User user) {
        String savedUserId = userService.create(user);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        if (savedUserId == null) {
            Message message = new Message(StatusEnum.CONFLICT, "중복 회원 존재!", null);
            return new ResponseEntity<>(message, headers, HttpStatus.CONFLICT);
        } else {
            Message message = new Message(StatusEnum.CREATED, "회원가입 성공!", user);
            return new ResponseEntity<>(message, headers, HttpStatus.CREATED);
        }
    }

    @GetMapping(value = "/user")
    @Operation(summary = "회원 정보 조회 메서드", description = "회원 정보 조회 메서드입니다.")
    public ResponseEntity<Message> read(@RequestParam String userId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        UserReadDTO user = userService.read(userId);
        if (user == null) {
            Message message = new Message(StatusEnum.NOT_FOUND, "존재하지 않는 아이디!", null);
            return new ResponseEntity<>(message, headers, HttpStatus.NOT_FOUND);
        } else {
            Message message = new Message(StatusEnum.OK, "유저 조회 성공!", user);
            return new ResponseEntity<>(message, headers, HttpStatus.OK);
        }
    }
}
