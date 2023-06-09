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

    public Long create(Room room) {
        Optional<Room> exist = roomRepository.findById(room.getRoomId());
        if (exist.isPresent()) {
            throw new IllegalStateException("존재하는 ID 입니다.");
        }
        roomRepository.save(room);
        return room.getRoomId();
    }

    public Room readOne(Long roomId) {
        return roomRepository.findById(roomId)
                .orElseThrow(() -> new IllegalStateException("없는 그룹ID 입니다."));
    }

    public List<Room> readAll() {
        List<Room> rooms = roomRepository.findAll();
        return rooms;
    }

    public Long update(Room room){
        Optional<Room> exist = roomRepository.findById(room.getRoomId());
        if (!exist.isPresent()) {
            throw new IllegalStateException("존재하지 않는 그룹 입니다.");
        }
        roomRepository.save(room);
        return room.getRoomId();
    }

    public void delete(Long roomId) {
        Room room = readOne(roomId);
        roomRepository.delete(room);
    }

}
