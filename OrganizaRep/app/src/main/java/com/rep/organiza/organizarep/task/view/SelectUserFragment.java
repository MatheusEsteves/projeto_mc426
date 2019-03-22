package com.rep.organiza.organizarep.task.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.rep.organiza.organizarep.R;
import com.rep.organiza.organizarep.base.BaseFragment;
import com.rep.organiza.organizarep.model.Task;
import com.rep.organiza.organizarep.model.User;
import com.rep.organiza.organizarep.Util.FragmentManager;
import com.rep.organiza.organizarep.task.model.UserSelect;
import com.rep.organiza.organizarep.task.presenter.SelectUserPresenter;
import com.rep.organiza.organizarep.task.view.adapters.UserAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SelectUserFragment extends BaseFragment {

    @Bind(R.id.rv_rep_members)
    RecyclerView recyclerView;

    private TaskActivity activity;
    private Button btCreateTask;
    private Task tarefa;
    private SelectUserPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        if(savedInstanceState != null){
            tarefa = (Task) savedInstanceState.get("nomeDoObj");
        }

        presenter = new SelectUserPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_attribution, container, false);
        ButterKnife.bind(this, view);
        activity = (TaskActivity) this.getActivity();
        btCreateTask = view.findViewById(R.id.bt_create_task);
        setCreateTaskButtonOnclick();
        presenter.loadUser();

        return view;
    }


    public void showUsers(List<UserSelect> users) {
        if (users != null && !users.isEmpty()) {
            UserAdapter adapter = new UserAdapter(users, this.getContext());

            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        } else {
            showEmptyState();
        }
    }

    private void showEmptyState() {
        Toast.makeText(null, "Não há usuários", Toast.LENGTH_LONG);
    }

    private void setCreateTaskButtonOnclick(){
        btCreateTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Instanciar um objeto tarefa  problema criar um array de usuarios em tarefa

                FragmentManager.replaceFragment(R.id.container_task, new ListTasksFragment(), "f3", true, getFragmentManager());
            }
        });
    }
}
