package com.rep.organiza.organizarep.model;

import com.rep.organiza.organizarep.task.model.WeekDay;

import java.util.List;

public class Task {
    private String title;
    private String Description;
    private String userImagePath;
    private String userName;
    private List<WeekDay> weekDays;

    public Task(String title, String description, String userImagePath, String userName, List<WeekDay> weekDays) {
        this.title = title;
        Description = description;
        this.userImagePath = userImagePath;
        this.userName = userName;
        this.weekDays = weekDays;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getUserImagePath() {
        return userImagePath;
    }

    public void setUserImagePath(String userImagePath) {
        this.userImagePath = userImagePath;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<WeekDay> getWeekDays() {
        return weekDays;
    }

    public void setWeekDays(List<WeekDay> weekDays) {
        this.weekDays = weekDays;
    }
}
