package com.repl.replnote.room.controller;

import com.repl.replnote.room.entity.Room;
import com.repl.replnote.room.service.RoomService;
import com.repl.replnote.todo.service.TodoService;
import com.repl.replnote.user.dao.LoginDAO;
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
@Tag(name = "Room", description = "Room 관련 api 입니다.")
public class RoomController {
    private final RoomService roomService;
    private final TodoService todoService;

    @Autowired
    public RoomController(RoomService roomService, TodoService todoService) {
        this.roomService = roomService;
        this.todoService = todoService;
    }

    @PostMapping(value = "/room", produces = "application/json;charset=UTF-8")
    @Operation(summary = "그룹 생성 메서드", description = "그룹 생성 메서드입니다.")
    public ResponseEntity<Message> create(@RequestBody Room room) {
        Long savedRoomId = roomService.create(room);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        if (savedRoomId == null) {
            Message message = new Message(StatusEnum.CONFLICT, "중복 그룹 존재!", null);
            return new ResponseEntity<>(message, headers, HttpStatus.CONFLICT);
        } else {
            Message message = new Message(StatusEnum.CREATED, "그룹 생성 성공!", room);
            return new ResponseEntity<>(message, headers, HttpStatus.CREATED);
        }
    }

    @PutMapping(value = "/room")
    @Operation(summary = "그룹 수정 메서드", description = "특정 그룹의 정보를 수정 메서드입니다.")
    public ResponseEntity<Message> update(@RequestBody Room room) {
        Long savedRoomId = roomService.update(room);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        if (savedRoomId == null) {
            Message message = new Message(StatusEnum.CONFLICT, "해당 그룹이 존재하지 않습니다!", null);
            return new ResponseEntity<>(message, headers, HttpStatus.CONFLICT);
        } else {
            Message message = new Message(StatusEnum.CREATED, "그룹 수정 성공!", room);
            return new ResponseEntity<>(message, headers, HttpStatus.CREATED);
        }
    }

    @GetMapping(value = "/room/{roomId}")
    @Operation(summary = "개별 그룹 조회 메서드", description = "roomId를 입력받아 특정 그룹을 조회 메서드입니다.")
    public Room readOne(@PathVariable("roomId") Long roomId) {
        return roomService.readOne(roomId);
    }

    @GetMapping(value = "/room")
    @Operation(summary = "전체 그룹 조회 메서드", description = "생성된 전체 그룹을 조회하는 메서드입니다.")
    public List<Room> readAll() {
        return roomService.readAll();
    }

    @DeleteMapping(value = "/room")
    @Operation(summary = "그룹 삭제 메서드", description = "roomId를 입력받아 특정 그룹을 삭제하는 메서드입니다.")
    public ResponseEntity<Message> delete(@RequestParam Long roomId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        todoService.deleteByRoomId(roomId);
        roomService.delete(roomId);
        Message message = new Message(StatusEnum.OK, "그룹 삭제 성공!",roomId);
        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }

}
