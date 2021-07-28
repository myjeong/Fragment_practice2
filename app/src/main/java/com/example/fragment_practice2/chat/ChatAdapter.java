package com.example.fragment_practice2.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragment_practice2.R;

import java.util.List;

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
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.setItem(chats.get(position));
        
        //클릭하면, 해당 아이템뷰의 위치를 나타내는 간단한 토스트메시지
        //뷰 홀더의, 내부 아이템들(리스트뷰의 경우 리스트1, 2, 3...)을 클릭한다 느낌
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context=view.getContext();
                Toast.makeText(context,position+"",Toast.LENGTH_SHORT).show();
            }
        });

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
