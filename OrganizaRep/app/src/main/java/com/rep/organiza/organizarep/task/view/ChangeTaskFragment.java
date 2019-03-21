package com.rep.organiza.organizarep.task.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rep.organiza.organizarep.R;
import com.rep.organiza.organizarep.Util.FragmentManager;
import com.rep.organiza.organizarep.base.BaseFragment;
import com.rep.organiza.organizarep.mock.UserAuthenticator;
import com.rep.organiza.organizarep.model.Task;
import com.rep.organiza.organizarep.task.presenter.ListTasksPresenter;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ChangeTaskFragment extends BaseFragment {

    private TaskActivity taskActivity;
    private Task taskOtherUser;

    @Bind(R.id.tv_change_task_text)
    TextView tvChangeTaskText;

    @Bind(R.id.tv_change_task_date)
    TextView tvChangeTaskDate;

    @Bind(R.id.tv_task_to_change_authenticated_user)
    TextView tvTaskToChangeAuthenticatedUser;

    @Bind(R.id.tv_task_to_change_other_user)
    TextView tvTaskToChangeOtherUser;

    @Bind(R.id.tv_cancel_change_task_action)
    TextView tvCancelChangeTaskAction;

    @Bind(R.id.tv_change_task_action)
    TextView tvChangeTaskAction;

    @Bind(R.id.cv_change_task_authenticated_user)
    CircleImageView cvChangeTaskAuthenticatedUser;

    @Bind(R.id.cv_change_task_other_user)
    CircleImageView cvChangeTaskOtherUser;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        Bundle arguments = this.getArguments();
        if (arguments != null){
            this.taskOtherUser = (Task) arguments.getSerializable("taskOtherUser");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_change_task, container, false);
        ButterKnife.bind(this, view);
        this.taskActivity = (TaskActivity)this.getActivity();
        this.showChangedTasks();
        this.setCancelChangeTaskOnClick();
        this.setChangeTaskOnClick();
        return view;
    }

    private void goBackToTaskList(){
        FragmentManager.removeFragment(this, taskActivity.getSupportFragmentManager());
    }

    private void setCancelChangeTaskOnClick(){
        tvCancelChangeTaskAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBackToTaskList();
            }
        });
    }

    private void setChangeTaskOnClick(){
        tvChangeTaskAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),
                        "Solicitação de troca para " + taskOtherUser.getUser().getUserName() +
                        " enviada com sucesso !",
                        Toast.LENGTH_LONG
                ).show();
                goBackToTaskList();
            }
        });
    }

    private void updateChangeTaskText(){
        tvChangeTaskText.setText("Você deseja trocar de tarefas com " + taskOtherUser.getUser().getUserName() + " ?");
    }

    private void updateChangeTaskDate(){
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        tvChangeTaskDate.setText("Data: " + df.format(new Date()));
    }

    private void updateAuthenticatedUserData(){
        tvTaskToChangeAuthenticatedUser.setText(taskOtherUser.getTitle());
        setImage(cvChangeTaskAuthenticatedUser, R.drawable.authenticated_user_img);
    }

    private void updateOtherUserData(){
        tvTaskToChangeOtherUser.setText(UserAuthenticator.getAuthenticatedUserTask().getTitle());
        setImage(cvChangeTaskOtherUser, R.drawable.user_img);
    }

    private void setImage(ImageView img, int id) {
        Picasso.with(this.getContext())
                .load(id) //TODO set this property to load the correct image
                .placeholder(R.color.colorNotFound)
                .into(img);
    }

    public void showChangedTasks(){
        updateChangeTaskText();
        updateChangeTaskDate();
        updateAuthenticatedUserData();
        updateOtherUserData();
    }
}
