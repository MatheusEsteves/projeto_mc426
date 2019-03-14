package com.rep.organiza.organizarep.task.view.adapters;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rep.organiza.organizarep.R;
import com.rep.organiza.organizarep.model.Task;
import com.rep.organiza.organizarep.task.model.Status;
import com.rep.organiza.organizarep.task.model.WeekDay;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private List<Task> listTask;
    private Context mContext;

    public TaskAdapter(List<Task> list, Context context) {
        this.listTask = list;
        mContext = context;
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(v);
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

        setImage(holder.cvUserImage, userImgPath);
        feedWeekDays(weekDays, holder);
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
                img.setColorFilter(getStatusColor(weekDay.getStatus()),  PorterDuff.Mode.SRC_IN);
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

    private void setImage(ImageView img, String imgPath) {
        Picasso.with(mContext)
                .load(R.drawable.user_img) //TODO set this property to load the correct image
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

            default:
                return ContextCompat.getColor(mContext, R.color.colorNotTaskDay);
        }
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

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
            ivAlert = itemView.findViewById(R.id.iv_alert);
            ivExchange = itemView.findViewById(R.id.iv_exchange);

            cvSunday = itemView.findViewById(R.id.cv_sunday_circle);
            cvMonday = itemView.findViewById(R.id.cv_monday_circle);
            cvTuesday = itemView.findViewById(R.id.cv_tuesday_circle);
            cvWednesday = itemView.findViewById(R.id.cv_wednesday_circle);
            cvThursday = itemView.findViewById(R.id.cv_thursday_circle);
            cvFriday = itemView.findViewById(R.id.cv_friday_circle);
            cvSaturday = itemView.findViewById(R.id.cv_saturday_circle);

            layout.setOnClickListener(this);
            setAlertOnclick();
            setExchangeOnclick();
        }

        private void setAlertOnclick(){
            ivAlert.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "Lembrete enviado!", Toast.LENGTH_LONG).show();
                }
            });
        }

        private void setExchangeOnclick(){
            ivExchange.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "Atividade trocada com sucesso!", Toast.LENGTH_LONG).show();
                }
            });
        }

        @Override
        public void onClick(View v) {
        }
    }
}