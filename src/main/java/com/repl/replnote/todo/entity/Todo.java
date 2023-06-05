package com.repl.replnote.todo.entity;

import com.repl.replnote.group.entity.Room;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "todo")
@EntityListeners(AuditingEntityListener.class)
public class Todo {
    @Id
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @Id
    @Column(name = "todo_id")
    private String todoId;


    @Column(name = "is_finished", columnDefinition = "TINYINT", length = 1)
    @ColumnDefault("0")
    private int isFinished;

    @Column(name = "isShared", columnDefinition = "TINYINT", length = 1)
    @ColumnDefault("0")
    private int isShared;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
