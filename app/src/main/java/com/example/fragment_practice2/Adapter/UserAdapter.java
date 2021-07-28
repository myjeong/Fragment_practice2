package com.example.fragment_practice2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fragment_practice2.Model.User;
import com.example.fragment_practice2.R;



import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>{

    private Context mContext;
    private List<User> mUser;

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

            username=itemView.findViewById(R.id.user_name);
            profile_image=itemView.findViewById(R.id.profile_image);

        }
    }

}
