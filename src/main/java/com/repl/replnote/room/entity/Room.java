package com.repl.replnote.room.entity;

import com.repl.replnote.user.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "room")
@EntityListeners(AuditingEntityListener.class)
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long groupId;

    @Column(length = 20)
    @NotNull
    private String name;

    @Column(length = 100)
    private String introduction;

    @ManyToOne
    @JoinColumn(name = "owner")
    private User owner;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany
    private List<User> users = new ArrayList<User>() ;


    public Long getGroupId() {
        return groupId;
    }
    public String getName() {
        return name;
    }
    public User getOwner() {
        return owner;
    }

}
