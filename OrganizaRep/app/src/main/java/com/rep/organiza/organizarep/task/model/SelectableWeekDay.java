package com.rep.organiza.organizarep.task.model;

public class SelectableWeekDay extends WeekDay {
    private boolean isSelected;

    public SelectableWeekDay(String name, boolean isSelected) {
        super(name, Status.notTaskDay);
        this.isSelected = isSelected;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        if(selected){
            this.setStatus(Status.toDo);
        }else{
            this.setStatus(Status.notTaskDay);
        }

        isSelected = selected;
    }
}
