package com.example.fragment_practice2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Objects;

//adapter: recyclerView 항목을 구성하는 필수 요소
//ViewHolder:각 항목 구성 뷰의 재활용을 목적으로 하는 필수 요소
//LayoutManager: 항목의 배치를 결정하는 필수 요소

//3. 이후 생성된 RecyclerView.Adapter<>를 상속받는다.
public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    private final List<Chat> chats;

    //1. ChatAdapter 클래스를 만들고 어댑터 생성자를 구축
    public ChatAdapter(List<Chat> chats){this.chats=chats;}

    //4. 어댑터를 설정한 뒤 onCreateViewHolder를 콜백하여 뷰를 넘겨준다.
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.chat_item, parent, false);

        return new ChatAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setItem(chats.get(position));

    }

    @Override
    public int getItemCount() {
        return chats.size();
    }

    //2. ViewHolder 클래스를 RecyclerView.ViewHolder를 상속하여 생성
    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView chatUsername;
        private TextView chatUsermsg;

        //5. 넘겨받은 view를 이용하여 아이디를 찾아 설정한다.
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            chatUsername = itemView.findViewById(R.id.user_name);
            chatUsermsg=itemView.findViewById(R.id.user_message);
        }
        public void setItem(Chat chat){
            chatUsername.setText(chat.getUsername());
            chatUsermsg.setText(chat.getMessage());
        }

    }

}
