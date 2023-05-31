package com.repl.replnote.user.service;

import com.repl.replnote.user.entity.User;
import com.repl.replnote.user.repository.SpringDataJpaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final SpringDataJpaUserRepository userRepository;

    @Autowired
    public UserService(SpringDataJpaUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String create(User user) {
        validateDuplicateUser(user);
        userRepository.save(user);
        return user.getUserId();
    }

//    public Optional<User> read() {
//
//    }
//
//    public int count() {
//
//    }
//
//    public List<User> list() {
//
//    }

    public void validateDuplicateUser(User user) {
        userRepository.findById(user.getUserId()).ifPresent(u -> {
            throw new IllegalStateException("이미 존재하는 회원입니다!");
        });
    }
}
