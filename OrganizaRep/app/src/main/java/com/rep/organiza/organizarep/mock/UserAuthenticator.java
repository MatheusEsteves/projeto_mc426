package com.rep.organiza.organizarep.mock;

import com.rep.organiza.organizarep.model.Task;
import com.rep.organiza.organizarep.model.User;

import java.util.List;

public class UserAuthenticator {

    private static Task authenticatedUserTask;

    public static void setAuthenticatedUserTask(Task task){
        UserAuthenticator.authenticatedUserTask = task;
    }

    public static User getAuthenticatedUser(){
        return UserAuthenticator.authenticatedUserTask.getUser();
    }

    public static Task getAuthenticatedUserTask(){
        return UserAuthenticator.authenticatedUserTask;
    }
}
