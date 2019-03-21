package com.rep.organiza.organizarep.task.presenter;

import com.rep.organiza.organizarep.base.BasePresenter;
import com.rep.organiza.organizarep.mock.UserAuthenticator;
import com.rep.organiza.organizarep.model.Task;
import com.rep.organiza.organizarep.model.User;
import com.rep.organiza.organizarep.task.model.Status;
import com.rep.organiza.organizarep.task.model.WeekDay;
import com.rep.organiza.organizarep.task.model.WeekDaySelectable;
import com.rep.organiza.organizarep.task.view.CreateTaskFragment;
import com.rep.organiza.organizarep.task.view.ListTasksFragment;

import java.util.ArrayList;
import java.util.List;

public class CreateTaskPresenter extends BasePresenter {
    private CreateTaskFragment fragment;
    private ArrayList<WeekDaySelectable> days;

    public CreateTaskPresenter(CreateTaskFragment fragment){
        this.fragment = fragment;
        this.days = new ArrayList<WeekDaySelectable>();

        mockTasks();
    }

    private void mockTasks(){
        this.days = new ArrayList<>();

        for(int i=0; i<7; i++) {
            WeekDaySelectable day = new WeekDaySelectable("Day"+i, false);
            days.add(day);
        }
    }



    public void loadTasks(){
        fragment.showDays(days);
    }
}
