package com.repl.replnote.user.controller;

import com.repl.replnote.user.entity.User;
import com.repl.replnote.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "User", description = "User 관련 api 입니다.")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/user")
    @ResponseStatus(value = HttpStatus.CREATED)
    @ResponseBody
    @Operation(summary = "회원가입 메서드", description = "회원가입 메서드입니다.")
    public String create(@RequestBody User user) {
        userService.create(user);
        return user.getUserId();
    }
}
