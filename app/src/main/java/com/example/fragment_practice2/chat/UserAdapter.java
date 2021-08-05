package com.example.fragment_practice2.chat;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fragment_practice2.MessageActivity;
import com.example.fragment_practice2.R;


import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>{

    private final Context mContext;
    private final ArrayList<User> mUser;

    public UserAdapter(Context mContext, ArrayList<User> mUser){
        this.mUser=mUser;
        this.mContext=mContext;
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

    //리사이클러뷰에 들어갈 뷰 홀더를 할당하는 함수
    //viewHolder를 새로 만들어야 할 때 호출되는 메소드
    //뷰홀더와 연결된 뷰를 생성 및 초기화 but 뷰의 컨텐츠를 채우지는 않음
    //R.layout.user_item : 뷰홀더용 xml
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new UserAdapter.ViewHolder(view);
    }

    //뷰홀더와 데이터를 연결할 때 호출되는 메소드
    //적절한 데이터를 가져와 뷰홀더의 레이아웃을 채움
    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {

        //Glide.with(holder.itemView).load(mUser.get(position).getImageUrl()).into(holder.profile_image);
        //holder.username.setText(mUser.get(position).getUsername());


        User user=mUser.get(position);
        holder.username.setText(user.getUsername());

/*
        아래 코드 실행시 에러
        if(user.getImageUrl().equals("default")){
            holder.profile_image.setImageResource(R.mipmap.ic_launcher);
        }

        else{
            Glide.with(mContext).load(user.getImageUrl()).into(holder.profile_image);
        }
*/
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext, MessageActivity.class);
                intent.putExtra("userId",user.getId());

                //Toast.makeText(mContext, "User ID: " + user.getId(), Toast.LENGTH_SHORT).show();

                mContext.startActivity(intent);
            }
        });


    }

    //데이터 세트의 크기를 호출할때 호출하는 메소드
    @Override
    public int getItemCount() {
        return (mUser!=null?mUser.size():0);
        //return mUser.size();
    }




}
