package com.rep.organiza.organizarep.task.presenter;

import com.rep.organiza.organizarep.base.BasePresenter;
import com.rep.organiza.organizarep.model.Task;
import com.rep.organiza.organizarep.model.User;
import com.rep.organiza.organizarep.task.model.Status;
import com.rep.organiza.organizarep.task.model.WeekDay;
import com.rep.organiza.organizarep.task.view.ListTasksFragment;

import java.util.ArrayList;
import java.util.List;

public class ListTasksPresenter extends BasePresenter {
    private ListTasksFragment fragment;
    private List<Task> list;

    public ListTasksPresenter(ListTasksFragment fragment){
        this.fragment = fragment;
        list = new ArrayList<Task>();

        for(int i=1; i<10; i++)
        mockData();
    }

    private void mockData() {
        List<WeekDay> days = new ArrayList<WeekDay>();

        WeekDay sun = new WeekDay("Dom", Status.done);
        days.add(sun);
        WeekDay mon = new WeekDay("Seg", Status.done);
        days.add(mon);
        WeekDay tue = new WeekDay("Ter", Status.exchanged);
        days.add(tue);
        WeekDay wed = new WeekDay("Qua", Status.notTaskDay);
        days.add(wed);
        WeekDay thu = new WeekDay("Qui", Status.notTaskDay);
        days.add(thu);
        WeekDay fri = new WeekDay("Sex", Status.late);
        days.add(fri);
        WeekDay sat = new WeekDay("Sab", Status.notTaskDay);
        days.add(sat);

        User user  = new User("Lulinha", "");
        Task task = new Task("Tirar o Lixo", "Retirar o lixo do banheiro", user , days);

        list.add(task);
    }

    public void loadTasks(){
        fragment.showTasks(list);
    }
}
