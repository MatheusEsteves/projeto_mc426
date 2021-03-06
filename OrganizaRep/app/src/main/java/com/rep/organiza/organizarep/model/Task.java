package com.rep.organiza.organizarep.model;

import com.rep.organiza.organizarep.task.model.WeekDay;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Task implements Serializable {
    private String title;
    private String Description;
    private User user;
    private List<WeekDay> weekDays;

    public Task(String title, String description, User user, List<WeekDay> weekDays) {
        this.title = title;
        Description = description;
        this.weekDays = weekDays;
        this.user = user;
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

    public List<WeekDay> getWeekDays() {
        return weekDays;
    }

    public void setWeekDays(List<WeekDay> weekDays) {
        this.weekDays = weekDays;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
