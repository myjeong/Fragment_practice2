package com.example.fragment_practice2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/*
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_chat#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_chat extends Fragment {
    private RecyclerView rvList;
    private ChatAdapter chatAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_chat, container, false);
        rvList=view.findViewById(R.id.recyclerview_chat);

        List<Chat> chats=new ArrayList<>();

        for(int i = 1; i < 10; i++){
            chats.add(new Chat(i, "user"+i, "message "+i));
        }
        LinearLayoutManager manager = new LinearLayoutManager(container.getContext(), RecyclerView.VERTICAL,false);
        rvList.setLayoutManager(manager);
        chatAdapter=new ChatAdapter(chats);
        rvList.setAdapter(chatAdapter);

        return view;


    }
}