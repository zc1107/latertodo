package com.example.latertodo;

import java.util.Date;

public class ToDoList {
    private String nameOfToDo;
    private String timeOfToDo;

    public ToDoList(){
        nameOfToDo = "待办事项";
        timeOfToDo = "15";
    }

    public ToDoList(String name,String time){
        this.nameOfToDo = name;
        this.timeOfToDo = time;
    }

    public String getNameOfToDo(){
        return nameOfToDo;
    }

    public String getTimeOfToDo(){
        return timeOfToDo;
    }

}
