package com.repl.replnote.todo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class TodoId implements Serializable {

    @Column(name = "todo_id")
    private String todoId;

    @Column(name = "room_id")
    private Long roomId;


    public String getTodoId(){
        return todoId;
    }
    public Long getRoomId(){return roomId;}

}
