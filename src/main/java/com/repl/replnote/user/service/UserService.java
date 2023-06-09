package com.repl.replnote.user.service;

import com.repl.replnote.user.dto.UserReadDTO;
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
        if (validateDuplicateUser(user)) {
            userRepository.save(user);
            return user.getUserId();
        } else {
            return null;
        }
    }

    public UserReadDTO read(String userId) {
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            return null;
        } else {
            return new UserReadDTO(user.get().getUserId(), user.get().getName());
        }
    }
//
//    public int count() {
//
//    }
//
//    public List<User> list() {
//
//    }

    public boolean validateDuplicateUser(User user) {
        if (userRepository.findById(user.getUserId()).isPresent()) {
            return false;
        } else {
            return true;
        }
    }
}
