package com.repl.replnote.group.entity;

import com.repl.replnote.user.entity.User;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "room_user")
@EntityListeners(AuditingEntityListener.class)
public class RoomUser {
    @Id
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
