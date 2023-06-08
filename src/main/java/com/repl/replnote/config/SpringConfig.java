package com.repl.replnote.config;

import com.repl.replnote.room.repository.SpringDataJpaRoomRepository;
import com.repl.replnote.user.repository.SpringDataJpaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
//@Import({SwaggerConfig.class})
public class SpringConfig {
    private final SpringDataJpaUserRepository userRepository;
    private final SpringDataJpaRoomRepository roomRepository;

    @Autowired
    public SpringConfig(SpringDataJpaUserRepository userRepository,SpringDataJpaRoomRepository roomRepository) {
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
    }


    // setter 주입
//    @Bean
//    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }
}