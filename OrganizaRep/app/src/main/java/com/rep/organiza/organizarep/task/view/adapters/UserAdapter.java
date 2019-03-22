package com.rep.organiza.organizarep.task.view.adapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rep.organiza.organizarep.R;
import com.rep.organiza.organizarep.model.User;
import com.rep.organiza.organizarep.task.listeners.OnItemSelectedListener;
import com.rep.organiza.organizarep.task.model.SelectableUser;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserAdapter extends RecyclerView.Adapter implements OnItemSelectedListener {
    private static final boolean SELECTED = true;
    private static final boolean UNSELECTED = false;

    private List<SelectableUser> listUser;
    private Context mContext;

    public UserAdapter(List<SelectableUser> list, Context context) {
        this.listUser = list;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rep_member, parent, false);

        return new UserViewHolder(v, this);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        bindUserViewHolder((UserViewHolder) holder, position);
    }

    private void bindUserViewHolder(UserViewHolder holder, int position) {
        UserViewHolder userViewHolder = holder;
        SelectableUser selectableUser = listUser.get(position);

        String userName = selectableUser.getUserName();
        String userImgPath = selectableUser.getUserImagePath();

        userViewHolder.tvUserName.setText(userName);
        setImage(userViewHolder.cvUserImage, userImgPath);
        holder.selectableUser = selectableUser;
        holder.setChecked(holder.selectableUser.isSelected());
    }

    @Override
    public void onItemSelected(SelectableUser item) {

        for (SelectableUser selectableUser : listUser) {
            if (selectableUser.equals(item) && !selectableUser.isSelected()) {
                selectableUser.setSelected(true);
            }else{
                if(!selectableUser.equals(item) && selectableUser.isSelected()) {
                    selectableUser.setSelected(false);
                }
            }
        }
        notifyDataSetChanged();
    }

    private void setImage(ImageView img, String imgPath) {
        Picasso.with(mContext)
                .load(R.drawable.user_img) //TODO set this property to load the correct image
                .placeholder(R.color.colorNotFound)
                .into(img);
    }

    @Override
    public int getItemCount() {
        return listUser != null ? listUser.size() : 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{
        TextView tvUserName;
        CircleImageView cvUserImage;
        ConstraintLayout layoutUserItem;
        ImageView ivCheckCircle;

        OnItemSelectedListener itemSelectedListener;
        SelectableUser selectableUser;

        public UserViewHolder(View itemView, OnItemSelectedListener listener) {
            super(itemView);

            this.itemSelectedListener = listener;
            tvUserName = itemView.findViewById(R.id.tv_user_name);
            cvUserImage = itemView.findViewById(R.id.cv_user_image);
            layoutUserItem = itemView.findViewById(R.id.layout_user_item);
            ivCheckCircle = itemView.findViewById(R.id.iv_check_circle);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                   if(ivCheckCircle.getVisibility() == View.VISIBLE){
                       setChecked(UNSELECTED);
                   }else{
                       setChecked(SELECTED);
                   }

                   itemSelectedListener.onItemSelected(selectableUser);
                }
            });
        }

       public void setChecked(boolean isSelected) {
            int color;

            if(isSelected){
                color = ContextCompat.getColor(itemView.getContext(), R.color.colorMemberSelected);
                ColorStateList tint = ColorStateList.valueOf(color);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    layoutUserItem.setBackgroundTintList(tint);
                }

                layoutUserItem.setBackgroundColor(color);
                ivCheckCircle.setVisibility(View.VISIBLE);
            }else {
                color = ContextCompat.getColor(itemView.getContext(), R.color.colorListItemDefault);
                ColorStateList tint = ColorStateList.valueOf(color);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    layoutUserItem.setBackgroundTintList(tint);
                }

                ivCheckCircle.setVisibility(View.INVISIBLE);
            }

            selectableUser.setSelected(isSelected);
        }
    }
}
