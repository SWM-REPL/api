package com.repl.replnote.todo.repository;

import com.repl.replnote.todo.entity.Todo;
import com.repl.replnote.todo.entity.TodoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface SpringDataJpaTodoRepository extends JpaRepository<Todo, TodoId>{

}
