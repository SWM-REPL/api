package com.repl.replnote.session.interceptor;

import com.repl.replnote.session.service.SessionService;
import com.repl.replnote.user.entity.User;
import com.repl.replnote.util.interceptor.InterceptorException;
import com.repl.replnote.util.interceptor.InterceptorExceptionEnum;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Autowired
    SessionService sessionService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getSession().getAttribute("userId") == null) {
            throw new InterceptorException(InterceptorExceptionEnum.UNAUTHORIZED);
        }
//        return true;
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
