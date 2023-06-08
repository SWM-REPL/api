package com.repl.replnote.room.service;

import com.repl.replnote.room.entity.Room;
import com.repl.replnote.room.repository.SpringDataJpaRoomRepository;
import com.repl.replnote.user.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@RequiredArgsConstructor
@Service
public class RoomService {
    private final SpringDataJpaRoomRepository roomRepository;

    @Autowired
    public RoomService(SpringDataJpaRoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Long createRoom(Room room) {

        Optional<Room> exist = roomRepository.findById(room.getGroupId());
        if (exist.isPresent()) {
            throw new IllegalStateException("존재하는 ID 입니다.");
        }
        roomRepository.save(room);
        return room.getGroupId();
    }

    public Room getRoomOne(Long groupId) {
        return roomRepository.findById(groupId)
                .orElseThrow(() -> new IllegalStateException("없는 그룹ID 입니다."));
    }
    public List<Room> getRoomAll() {
        List<Room> rooms = roomRepository.findAll();
        return rooms;
    }

    public Long updateRoom(Room room){

        Optional<Room> exist = roomRepository.findById(room.getGroupId());
        if (!exist.isPresent()) {
            throw new IllegalStateException("존재하지 않는 그룹 입니다.");
        }
        roomRepository.save(room);
        return room.getGroupId();
    }

    public void deleteRoom(Long groupId) {
        Room room = getRoomOne(groupId);
        roomRepository.delete(room);
    }
    public boolean validateDuplicateUser(Room room) {
        if (roomRepository.findById(room.getGroupId()).isPresent()) {
            return false;
        } else {
            return true;
        }
    }
}
