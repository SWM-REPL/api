package com.repl.replnote.user.controller;

import com.repl.replnote.user.entity.User;
import com.repl.replnote.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/user")
    @ResponseStatus(value = HttpStatus.CREATED)
    @ResponseBody
    public String create(@RequestBody User user) {
        userService.create(user);
        return user.getUserId();
    }
}
