package com.repl.replnote.room.controller;

import com.repl.replnote.room.entity.Room;
import com.repl.replnote.room.service.RoomService;
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

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }
    @PostMapping(value = "/create-room")
    @Operation(summary = "그룹 생성 메서드", description = "그룹 생성 메서드입니다.")
    public ResponseEntity<Message> createRoom(@RequestBody Room room) {
        Long savedRoomId = roomService.createRoom(room);
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
    @PutMapping(value = "/update-room")
    @Operation(summary = "그룹 수정 메서드", description = "그룹 수정 메서드입니다.")
    public ResponseEntity<Message> updateRoom(@RequestBody Room room) {
        Long savedRoomId=roomService.updateRoom(room);
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
    @GetMapping(value = "/{groupId}")
    public Room getRoomOne(@PathVariable("groupId") Long groupId){
        return roomService.getRoomOne(groupId);
    }

    @GetMapping(value = "/group-list")
    public List<Room> getRoomAll(){
        return roomService.getRoomAll();
    }

    @RequestMapping(value = "/{groupId}", method = RequestMethod.DELETE)
    public void deleteRoom(@PathVariable("groupId") Long groupId) {
        roomService.deleteRoom(groupId);
    }

}
