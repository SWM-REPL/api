package com.repl.replnote;

import com.repl.replnote.user.repository.SpringDataJpaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    private final SpringDataJpaUserRepository userRepository;

    @Autowired
    public SpringConfig(SpringDataJpaUserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
