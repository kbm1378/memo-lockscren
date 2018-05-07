package com.example.byungmook.memolock.model;

import io.realm.RealmObject;
import io.realm.annotations.Required;

public class Todo extends RealmObject {
    @Required
    private String todo;

    public void setTodo(String todo){
        this.todo = todo;
    }

    public String getTodo(){
        return todo;
    }
}

