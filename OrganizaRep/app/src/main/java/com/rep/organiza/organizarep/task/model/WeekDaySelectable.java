package com.rep.organiza.organizarep.task.model;

public class WeekDaySelectable extends WeekDay {
    private boolean isSelected;

    public WeekDaySelectable(String name, boolean isSelected) {
        super();
        this.name = name;
        this.isSelected = isSelected;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
