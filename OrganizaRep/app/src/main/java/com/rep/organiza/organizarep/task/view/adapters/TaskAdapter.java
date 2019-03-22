package com.rep.organiza.organizarep.task.view.adapters;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.opengl.Visibility;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rep.organiza.organizarep.Constants;
import com.rep.organiza.organizarep.R;
import com.rep.organiza.organizarep.Util.FragmentManager;
import com.rep.organiza.organizarep.base.BaseActivity;
import com.rep.organiza.organizarep.mock.UserAuthenticator;
import com.rep.organiza.organizarep.model.Task;
import com.rep.organiza.organizarep.task.model.Status;
import com.rep.organiza.organizarep.task.model.WeekDay;
import com.rep.organiza.organizarep.task.view.ChangeTaskFragment;
import com.rep.organiza.organizarep.task.view.TaskActivity;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private ArrayList<Task> listTask;
    private Context mContext;
    private TaskActivity taskActivity;

    public TaskAdapter(ArrayList<Task> list, Context context, TaskActivity taskActivity) {
        this.listTask = list;
        this.mContext = context;
        this.taskActivity = taskActivity;
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(v);
    }

    private void costumizeAuthenticatedUserTask(TaskViewHolder holder, Task task){
        holder.ivAlert.setVisibility(View.GONE);
        holder.ivExchange.setVisibility(View.GONE);
        setImage(holder.cvUserImage, R.drawable.authenticated_user_img);
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        Task task = listTask.get(position);

        String taskTitle = task.getTitle();
        String taskDescription = task.getDescription();
        String userName = task.getUser().getUserName();
        String userImgPath = task.getUser().getUserImagePath();
        List<WeekDay> weekDays = task.getWeekDays();

        holder.tvTaskTitle.setText(taskTitle);
        holder.tvTaskDescription.setText(taskDescription);
        holder.tvUserName.setText(userName);
        feedWeekDays(weekDays, holder);

        if (task.getUser().equals(UserAuthenticator.getAuthenticatedUser())){
            costumizeAuthenticatedUserTask(holder,task);
        } else {
            holder.setExchangeOnclick(task);
            setImage(holder.cvUserImage, R.drawable.user_img);
        }
    }

    private void feedWeekDays(List<WeekDay> weekDays, TaskViewHolder holder) {
        List<CircleImageView> listCircles = makeCircleImages(holder);
        WeekDay weekDay;
        CircleImageView img;

        for (int i=0; i<7; i++){
            weekDay = weekDays.get(i);
            img = listCircles.get(i);

            if(weekDay.getStatus() == Status.exchanged){
                Drawable icon = ContextCompat.getDrawable(mContext, R.drawable.ic_exchange);
                int color = ContextCompat.getColor(mContext, R.color.colorDoneTask);
                icon.setColorFilter(color, PorterDuff.Mode.SRC_IN);
                img.setImageDrawable(icon);
            }else{
                img.setColorFilter(getStatusColor(weekDay.getStatus()),  PorterDuff.Mode.SRC);
            }
        }
    }

    private List<CircleImageView> makeCircleImages(TaskViewHolder holder) {
        List list = new ArrayList<CircleImageView>();
        list.add(holder.cvSunday);
        list.add(holder.cvMonday);
        list.add(holder.cvTuesday);
        list.add(holder.cvWednesday);
        list.add(holder.cvThursday);
        list.add(holder.cvFriday);
        list.add(holder.cvSaturday);

        return list;
    }

    private void setImage(ImageView img, int id) {
        Picasso.with(mContext)
                .load(id) //TODO set this property to load the correct image
                .placeholder(R.color.colorNotFound)
                .into(img);
    }

    @Override
    public int getItemCount() {
        return listTask != null ? listTask.size() : 0;
    }

    public int getStatusColor(Status status){
        switch (status){
            case done:
            case exchanged:
                return ContextCompat.getColor(mContext, R.color.colorDoneTask);

            case late:
                return ContextCompat.getColor(mContext, R.color.colorLateTask);

            case toDo:
                return ContextCompat.getColor(mContext, R.color.colorToDoTask);

            default:
                return ContextCompat.getColor(mContext, R.color.colorNotTaskDay);
        }
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder{

        ConstraintLayout layout;
        TextView tvTaskTitle;
        TextView tvTaskDescription;
        TextView tvUserName;
        CircleImageView cvUserImage;
        ImageView ivAlert;
        ImageView ivExchange;

        CircleImageView cvSunday;
        CircleImageView cvMonday;
        CircleImageView cvTuesday;
        CircleImageView cvWednesday;
        CircleImageView cvThursday;
        CircleImageView cvFriday;
        CircleImageView cvSaturday;

        public TaskViewHolder(View itemView) {
            super(itemView);

            layout = itemView.findViewById(R.id.container_task);
            tvTaskTitle = itemView.findViewById(R.id.tv_task_title);
            tvTaskDescription = itemView.findViewById(R.id.tv_task_description);
            tvUserName = itemView.findViewById(R.id.tv_user_name);
            cvUserImage = itemView.findViewById(R.id.cv_user_image);

            cvSunday = itemView.findViewById(R.id.cv_sunday_circle);
            cvMonday = itemView.findViewById(R.id.cv_monday_circle);
            cvTuesday = itemView.findViewById(R.id.cv_tuesday_circle);
            cvWednesday = itemView.findViewById(R.id.cv_wednesday_circle);
            cvThursday = itemView.findViewById(R.id.cv_thursday_circle);
            cvFriday = itemView.findViewById(R.id.cv_friday_circle);
            cvSaturday = itemView.findViewById(R.id.cv_saturday_circle);

            ivAlert = itemView.findViewById(R.id.iv_alert);
            ivExchange = itemView.findViewById(R.id.iv_exchange);
            setAlertOnclick();
        }

        public void setAlertOnclick(){
            ivAlert.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "Lembrete enviado!", Toast.LENGTH_LONG).show();
                }
            });
        }

        public void setExchangeOnclick(Task taskOtherUser){
            ivExchange.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String changeTaskFragmentIdentification = "frag_1";

                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Constants.DATATRANSFERING_FROM_TASKITEM_TO_CHANGETASK, taskOtherUser);

                    ChangeTaskFragment changeTaskFragment = new ChangeTaskFragment();
                    changeTaskFragment.setArguments(bundle);

                    FragmentManager.replaceFragment(R.id.container_change_task,
                            changeTaskFragment, changeTaskFragmentIdentification, false,
                            taskActivity.getSupportFragmentManager());
                }
            });
        }
    }
}