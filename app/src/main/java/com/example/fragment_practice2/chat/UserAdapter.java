package com.example.fragment_practice2.chat;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fragment_practice2.MessageActivity;
import com.example.fragment_practice2.R;



import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>{

    private final Context mContext;
    private final List<User> mUser;

    public UserAdapter(Context mContext, List<User> mUser){
        this.mUser=mUser;
        this.mContext=mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.user_item, parent, false);
        return new UserAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {

        User user=mUser.get(position);
        holder.username.setText(user.getUsername());

        if(user.getImageUrl().equals("default")){
            holder.profile_image.setImageResource(R.mipmap.ic_launcher);
        }

        else{
            Glide.with(mContext).load(user.getImageUrl()).into(holder.profile_image);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext, MessageActivity.class);
                intent.putExtra("userId",user.getId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mUser.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView username;
        public ImageView profile_image;

        public ViewHolder(View itemView){
            super(itemView);

            //user_item.xml에 존재
            username=itemView.findViewById(R.id.user_name);
            profile_image=itemView.findViewById(R.id.profile_image);

        }

    }

}
