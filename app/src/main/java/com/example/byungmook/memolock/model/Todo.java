package com.example.byungmook.memolock.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Todo extends RealmObject {
    @PrimaryKey
    private int id;
    @Required
    private String todo;

    public void setId(int id) {this.id = id;}

    public void setTodo(String todo){
        this.todo = todo;
    }

    public String getTodo(){
        return todo;
    }

    public int getId() { return id; }
}

