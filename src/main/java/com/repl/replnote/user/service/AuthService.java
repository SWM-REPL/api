package com.repl.replnote.user.service;

import com.repl.replnote.user.dao.LoginDAO;
import com.repl.replnote.user.entity.User;
import com.repl.replnote.user.repository.SpringDataJpaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    //    @Autowired        : 필드 주입
    private final SpringDataJpaUserRepository userRepository;

    @Autowired      // 생성자 주입
    public AuthService(SpringDataJpaUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Boolean login(LoginDAO loginDAO) {
        Optional<User> findUser = userRepository.findById(loginDAO.getUserId());
        if (!findUser.isPresent()) {
            throw new IllegalStateException("존재하지 않는 회원입니다!");
        } else {
            if (findUser.get().getPassword().equals(loginDAO.getPassword())) {
                return true;
            } else {
                throw new IllegalStateException("존재하지 않는 회원입니다!");
            }
        }
    }
}
