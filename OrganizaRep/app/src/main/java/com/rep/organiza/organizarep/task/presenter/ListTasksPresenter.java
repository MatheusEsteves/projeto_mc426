package com.rep.organiza.organizarep.task.presenter;

import com.rep.organiza.organizarep.base.BasePresenter;
import com.rep.organiza.organizarep.mock.MockTasks;
import com.rep.organiza.organizarep.mock.UserAuthenticator;
import com.rep.organiza.organizarep.model.Task;
import com.rep.organiza.organizarep.model.User;
import com.rep.organiza.organizarep.task.model.Status;
import com.rep.organiza.organizarep.task.model.WeekDay;
import com.rep.organiza.organizarep.task.view.ListTasksFragment;

import java.util.ArrayList;
import java.util.List;

public class ListTasksPresenter extends BasePresenter {
    private static boolean isDataLoaded = false;
    private ListTasksFragment fragment;

    public ListTasksPresenter(ListTasksFragment fragment){
        this.fragment = fragment;

        if(!isDataLoaded) {
            User user  = new User("Eu", "../authenticated_user_img.jpg", "matheusesteveszanoto@gmail.com");
            mockTasksAuthenticatedUser(user);

            for (int i = 1; i < 10; i++) {
                mockTasksOtherUsers(i);
            }

            isDataLoaded = true;
        }
    }

    private void mockTasksAuthenticatedUser(User authenticatedUser){
        List<WeekDay> days = new ArrayList<WeekDay>();

        WeekDay sun = new WeekDay("Dom", Status.done);
        days.add(sun);
        WeekDay mon = new WeekDay("Seg", Status.notTaskDay);
        days.add(mon);
        WeekDay tue = new WeekDay("Ter", Status.notTaskDay);
        days.add(tue);
        WeekDay wed = new WeekDay("Qua", Status.done);
        days.add(wed);
        WeekDay thu = new WeekDay("Qui", Status.late);
        days.add(thu);
        WeekDay fri = new WeekDay("Sex", Status.notTaskDay);
        days.add(fri);
        WeekDay sat = new WeekDay("Sab", Status.notTaskDay);
        days.add(sat);

        Task task = new Task("Minha atividade", "A garagem deverá ser muito bem limpada", authenticatedUser , days);
        UserAuthenticator.setAuthenticatedUserTask(task);
        MockTasks.addTask(task);
    }

    private void mockTasksOtherUsers(int i){
        List<WeekDay> days = new ArrayList<WeekDay>();

        WeekDay sun = new WeekDay("Dom", Status.done);
        days.add(sun);
        WeekDay mon = new WeekDay("Seg", Status.notTaskDay);
        days.add(mon);
        WeekDay tue = new WeekDay("Ter", Status.exchanged);
        days.add(tue);
        WeekDay wed = new WeekDay("Qua", Status.notTaskDay);
        days.add(wed);
        WeekDay thu = new WeekDay("Qui", Status.late);
        days.add(thu);
        WeekDay fri = new WeekDay("Sex", Status.toDo);
        days.add(fri);
        WeekDay sat = new WeekDay("Sab", Status.notTaskDay);
        days.add(sat);

        User user  = new User("Lula " + i, "../user_img.jpg", "otheruser@gmail.com");
        Task task = new Task("Limpar banheiro " + i, "Banheiro deverá ser limpo muito bem limpado", user , days);

        MockTasks.addTask(task);
    }


    public void loadTasks(){
        fragment.showTasks(MockTasks.getTasks());
    }
}
