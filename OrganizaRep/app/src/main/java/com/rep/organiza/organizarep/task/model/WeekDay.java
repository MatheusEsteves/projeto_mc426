package com.rep.organiza.organizarep.task.model;

import java.io.Serializable;

public class WeekDay implements Serializable {
    private String name;
    private Status status;

    public WeekDay(String name, Status status) {
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
