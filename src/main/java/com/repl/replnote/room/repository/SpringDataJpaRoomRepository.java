package com.repl.replnote.room.repository;

import com.repl.replnote.room.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataJpaRoomRepository extends JpaRepository<Room, Long> {

}
