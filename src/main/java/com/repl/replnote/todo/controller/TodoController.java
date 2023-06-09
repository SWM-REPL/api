package com.repl.replnote.todo.controller;

import com.repl.replnote.room.entity.Room;
import com.repl.replnote.todo.entity.Todo;
import com.repl.replnote.todo.entity.TodoId;
import com.repl.replnote.todo.service.TodoService;
import com.repl.replnote.util.Message;
import com.repl.replnote.util.StatusEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.List;

@RestController
@Tag(name = "Todo", description = "Todo 관련 api 입니다.")
public class TodoController {
    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping(value = "/todo")
    @Operation(summary = "todo 생성 메서드", description = "todo 생성 메서드입니다.")
    public ResponseEntity<Message> createTodo(@RequestBody Todo todo) {
        TodoId savedTodoId = todoService.createTodo(todo);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        if (savedTodoId == null) {
            Message message = new Message(StatusEnum.CONFLICT, "중복 todo 존재!", null);
            return new ResponseEntity<>(message, headers, HttpStatus.CONFLICT);
        } else {
            Message message = new Message(StatusEnum.CREATED, "todo 생성 성공!", todo);
            return new ResponseEntity<>(message, headers, HttpStatus.CREATED);
        }
    }

    @PutMapping(value = "/todo")
    @Operation(summary = "todo 수정 메서드", description = "todo 수정 메서드입니다.")
    public ResponseEntity<Message> updateTodo(@RequestBody Todo todo) {
        TodoId savedTodoId = todoService.updateTodo(todo);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        if (savedTodoId == null) {
            Message message = new Message(StatusEnum.CONFLICT, "해당 그룹이 존재하지 않습니다!", null);
            return new ResponseEntity<>(message, headers, HttpStatus.CONFLICT);
        } else {
            Message message = new Message(StatusEnum.CREATED, "그룹 수정 성공!", todo);
            return new ResponseEntity<>(message, headers, HttpStatus.CREATED);
        }
    }

    @GetMapping(value = "/todo/{roomId}/{todoId}")
    public Todo getTodoOne(@PathVariable("roomId") Long roomId, @PathVariable("todoId") String todoId) {

        return todoService.getTodoOne(roomId,todoId);
    }

    @GetMapping(value = "/todo")
    public List<Todo> getTodoAll() {
        return todoService.getTodoAll();
    }

    @DeleteMapping(value = "/todo")
    public void deleteTodo(@RequestBody TodoId todoId) {
        todoService.deleteTodo(todoId.getRoomId(),todoId.getTodoId());
    }
}
