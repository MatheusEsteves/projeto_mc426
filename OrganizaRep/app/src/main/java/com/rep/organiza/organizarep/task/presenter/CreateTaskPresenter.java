package com.rep.organiza.organizarep.task.presenter;

import com.rep.organiza.organizarep.base.BasePresenter;
import com.rep.organiza.organizarep.task.model.SelectableUser;
import com.rep.organiza.organizarep.task.model.WeekDay;
import com.rep.organiza.organizarep.task.model.SelectableWeekDay;
import com.rep.organiza.organizarep.task.view.CreateTaskFragment;

import java.util.ArrayList;
import java.util.List;

public class CreateTaskPresenter extends BasePresenter {
    private CreateTaskFragment fragment;
    private ArrayList<SelectableWeekDay> days;

    public CreateTaskPresenter(CreateTaskFragment fragment){
        this.fragment = fragment;
        initializeWeekDays();
    }

    private void initializeWeekDays(){
        this.days = new ArrayList();
        days.add(new SelectableWeekDay("Dom", false));
        days.add(new SelectableWeekDay("Seg", false));
        days.add(new SelectableWeekDay("Ter", false));
        days.add(new SelectableWeekDay("Qua", false));
        days.add(new SelectableWeekDay("Qui", false));
        days.add(new SelectableWeekDay("Sex", false));
        days.add(new SelectableWeekDay("Sab", false));
    }

    public void loadTasks(){
        fragment.showDays(days);
    }

    public List<WeekDay> getWeekDays(){
        List<WeekDay> list = new ArrayList<>();

        for(SelectableWeekDay day: days){
            list.add(day);
        }

        return list;
    }
}
