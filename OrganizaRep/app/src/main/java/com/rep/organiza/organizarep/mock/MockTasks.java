package com.rep.organiza.organizarep.mock;

import com.rep.organiza.organizarep.model.Task;

import java.util.ArrayList;

public class MockTasks {
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void addTask(Task task){
        tasks.add(task);
    }

    public static ArrayList<Task> getTasks(){
        return tasks;
    }
}
