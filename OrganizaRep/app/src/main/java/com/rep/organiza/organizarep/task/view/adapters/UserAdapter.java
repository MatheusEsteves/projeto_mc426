package com.rep.organiza.organizarep.task.view.adapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
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
import com.rep.organiza.organizarep.model.User;
import com.rep.organiza.organizarep.task.model.UserSelect;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserAdapter extends RecyclerView.Adapter {
    private static final boolean SELECTED = true;
    private static final boolean UNSELECTED = false;

    private List<UserSelect> listUser;
    private Context mContext;

    public UserAdapter(List<UserSelect> list, Context context) {
        this.listUser = list;
        mContext = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rep_member_selected, parent, false);

        return new UserViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        bindUserViewHolder((UserViewHolder) holder, position);
    }

    private void bindUserViewHolder(UserViewHolder holder, int position) {
        UserViewHolder userViewHolder = holder;

        User user = listUser.get(position);

        String userName = user.getUserName();
        String userImgPath = user.getUserImagePath();

        userViewHolder.tvUserName.setText(userName);

        setImage(userViewHolder.cvUserImage, userImgPath);
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

    public class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvUserName;
        CircleImageView cvUserImage;
        ConstraintLayout layoutUserItem;
        ImageView ivCheckCircle;

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public UserViewHolder(View itemView) {
            super(itemView);

            tvUserName = itemView.findViewById(R.id.tv_user_name);
            cvUserImage = itemView.findViewById(R.id.cv_user_image);
            layoutUserItem = itemView.findViewById(R.id.layout_user_item);
            ivCheckCircle = itemView.findViewById(R.id.iv_check_circle);

            setSelection(false);

            itemView.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onClick(View view) {
                   if(ivCheckCircle.getVisibility() == View.VISIBLE){
                       setSelection(UNSELECTED);
                   }else{
                       setSelection(SELECTED);
                   }
                }
            });
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(mContext, "Usuário selecionado", Toast.LENGTH_LONG).show();

        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public void setSelection(boolean isSelected) {
            int color;

            if(isSelected){
                color = ContextCompat.getColor(itemView.getContext(), R.color.colorMemberSelected);
                ColorStateList tint = ColorStateList.valueOf(color);
                layoutUserItem.setBackgroundTintList(tint);
                layoutUserItem.setBackgroundColor(color);

                ivCheckCircle.setVisibility(View.VISIBLE);
            }else {
                color = ContextCompat.getColor(itemView.getContext(), R.color.colorListItemDefault);
                ColorStateList tint = ColorStateList.valueOf(color);
                layoutUserItem.setBackgroundTintList(tint);

                ivCheckCircle.setVisibility(View.INVISIBLE);
            }
        }
    }
}
