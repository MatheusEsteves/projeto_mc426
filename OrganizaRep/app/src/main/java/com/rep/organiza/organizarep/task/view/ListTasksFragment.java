package com.rep.organiza.organizarep.task.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.rep.organiza.organizarep.R;
import com.rep.organiza.organizarep.base.BaseFragment;
import com.rep.organiza.organizarep.model.Task;
import com.rep.organiza.organizarep.task.presenter.ListTasksPresenter;
import com.rep.organiza.organizarep.task.view.adapters.TaskAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ListTasksFragment extends BaseFragment{

    @Bind(R.id.rv_tasks)
    RecyclerView recyclerView;

    private TaskActivity activity;
    private ListTasksPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        presenter = new ListTasksPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_task, container, false);
        ButterKnife.bind(this, view);
        activity = (TaskActivity) this.getActivity();
        presenter.loadTasks();

        return view;
    }

    public void showTasks(ArrayList<Task> tasks) {
        if (tasks != null && !tasks.isEmpty()) {
            TaskAdapter adapter = new TaskAdapter(tasks, this.getContext(), this);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        } else {
            showEmptyState();
        }
    }

    private void showEmptyState() {
        Toast.makeText(null, "Não há atividades", Toast.LENGTH_LONG);
    }

    public RecyclerView getRecyclerView(){
        return this.recyclerView;
    }
}
