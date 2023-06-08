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
        return true;
//        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
//        System.out.println("Start afterCompletion");
//        Cookie[] cookies = request.getCookies();
//        if (cookies != null) {
//            System.out.println("Exist Cookie");
//            for (Cookie cookie : cookies) {
//                System.out.println(cookie.getName());
////                if (cookie.getName().equals("JSESSIONID")) {
////                    String userId = response.toString();
////                    System.out.println(userId);
//////                    sessionService.saveSession(cookie.getValue(), userId);
////                    break;
////                }
//            }
//        }
//        System.out.println("End afterCompletion");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
//        System.out.println("Start afterCompletion");
//        Cookie[] cookies = request.getCookies();
//        if (cookies != null) {
//            System.out.println("Exist Cookie");
//            for (Cookie cookie : cookies) {
//                System.out.println(cookie.getName());
////                if (cookie.getName().equals("JSESSIONID")) {
////                    String userId = response.toString();
////                    System.out.println(userId);
//////                    sessionService.saveSession(cookie.getValue(), userId);
////                    break;
////                }
//            }
//        }
//        System.out.println("End afterCompletion");
    }
}
