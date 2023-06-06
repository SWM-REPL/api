package com.repl.replnote.user.controller;

import com.repl.replnote.session.entity.Session;
import com.repl.replnote.session.service.SessionService;
import com.repl.replnote.user.dao.LoginDAO;
import com.repl.replnote.user.dto.LoginDTO;
import com.repl.replnote.user.entity.User;
import com.repl.replnote.user.service.AuthService;
import com.repl.replnote.user.service.UserService;
import com.repl.replnote.util.Message;
import com.repl.replnote.util.StatusEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;

@RestController
@Tag(name = "Auth", description = "Auth 관련 api 입니다.")
public class AuthController {
    private final AuthService authService;
    private final SessionService sessionService;
    private final UserService userService;

    @Autowired
    public AuthController(AuthService authService, SessionService sessionService, UserService userService) {
        this.authService = authService;
        this.sessionService = sessionService;
        this.userService = userService;
    }

    @PostMapping(value = "/auth")
    @Operation(summary = "로그인 메서드", description = "로그인 메서드입니다.")
    public ResponseEntity<Message> login(HttpServletRequest request, @RequestBody LoginDAO loginDAO) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        HttpSession session = request.getSession();
        Cookie[] cookies = request.getCookies();

        if (authService.login(loginDAO)) {
            Message message = new Message(StatusEnum.OK, "로그인 성공!", new LoginDTO(loginDAO.getUserId()));
            if (cookies != null) {
                for (Cookie cookie: cookies) {
                    if (cookie.getName().equals("JSESSIONID")) {
                        sessionService.saveSession(cookie.getValue(), loginDAO.getUserId());
                        break;
                    }
                }
            }
            return new ResponseEntity<>(message, headers, HttpStatus.OK);
        } else {
            Message message = new Message(StatusEnum.FORBIDDEN, "존재하지 않는 회원입니다!", null);
            return new ResponseEntity<>(message, headers, HttpStatus.FORBIDDEN);
        }
    }
}
