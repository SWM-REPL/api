package com.repl.replnote.todo.service;

import com.repl.replnote.room.entity.Room;
import com.repl.replnote.room.repository.SpringDataJpaRoomRepository;
import com.repl.replnote.todo.entity.Todo;
import com.repl.replnote.todo.entity.TodoId;
import com.repl.replnote.todo.repository.SpringDataJpaTodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class TodoService {
    private final SpringDataJpaTodoRepository todoRepository;


    @Autowired
    public TodoService(SpringDataJpaTodoRepository todoRepository) {
        this.todoRepository = todoRepository;

    }

    public TodoId createTodo(Todo todo){
        Optional<Todo> exist = todoRepository.findById(todo.getTodoId());
        if (exist.isPresent()) {
            throw new IllegalStateException("존재하는 todo 입니다.");
        }
        todoRepository.save(todo);
        return todo.getTodoId();
    }

    public void deleteByGroupId(Long roomId) {
        List<Todo> exist =todoRepository.findAll().stream().filter(todo -> todo.getTodoId().getRoomId().equals(roomId)).toList();

        for (Todo todo : exist) {
            todoRepository.delete(todo);
        }
    }
    public TodoId updateTodo(Todo todo){
        Optional<Todo> exist = todoRepository.findById(todo.getTodoId());
        if (!exist.isPresent()) {
            throw new IllegalStateException("존재하지 않는 그룹 입니다.");
        }
        todoRepository.save(todo);
        return todo.getTodoId();
    }
    public Todo getTodoOne(Long roomId, String todoId) {

        return todoRepository.findAll().stream()
                .filter(todo -> todo.getTodoId().getRoomId().equals(roomId)&&todo.getTodoId().getTodoId().equals(todoId))
                .findFirst().orElseThrow(()-> new IllegalStateException("없는 할일,그룹ID 입니다."));
    }
    public List<Todo> getTodoAll() {
        List<Todo> todos = todoRepository.findAll();
        return todos;
    }
    public void deleteTodo(Long roomId, String todoId) {
        Todo todo = getTodoOne(roomId,todoId);
        todoRepository.delete(todo);

    }

}
