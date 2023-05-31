package com.repl.replnote.user.repository;

import com.repl.replnote.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataJpaUserRepository extends JpaRepository<User, String> {
}
