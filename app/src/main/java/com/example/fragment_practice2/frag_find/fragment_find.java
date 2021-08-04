package com.example.fragment_practice2.frag_find;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.example.fragment_practice2.R;

import java.util.ArrayList;
import java.util.List;

public class fragment_find extends Fragment {
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_find, container, false);

        //키패드
        LinearLayout layout = v.findViewById(R.id.layout);
        layout.setOnTouchListener((v1, event) -> {
            hideKeyboard();
            return false;
        });

        //그리드뷰
        List<GridItemFind> itemsList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            itemsList.add(new GridItemFind(R.drawable.image_we_ta_in, "수정님 영어재능", "영어 1타 강사입니다."));
        }

        GridView gridView = v.findViewById(R.id.gridview);
        GridAdapterFind userAdapter = new GridAdapterFind(v.getContext(), R.layout.gridview_item_find, itemsList);
        gridView.setAdapter(userAdapter);

        List<GridItemFindIntent> itemsList2 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            itemsList2.add(new GridItemFindIntent("영어재능", "살면서 꼭 배워야할 언어", "어디서든 사용함", "어려움"));
        }

        GridView gridView2 = v.findViewById(R.id.gridview2);
        gridView2.setVisibility(View.INVISIBLE);
        com.example.fragment_practice2.frag_find.GridAdapterFindIntent userAdapter2 = new com.example.fragment_practice2.frag_find.GridAdapterFindIntent(v.getContext(), R.layout.gridview_item_find_intent, itemsList2);
        gridView2.setAdapter(userAdapter2);

        EditText search = v.findViewById(R.id.search);

        //버튼
        Button we_ta_in = v.findViewById(R.id.we_ta_in);
        Button intent = v.findViewById(R.id.intent);

        intent.setBackgroundColor(Color.parseColor("#ECE7EE"));
        we_ta_in.setTextColor(Color.WHITE);
        intent.setTextColor(Color.BLACK);

        we_ta_in.setOnClickListener(view -> {
            we_ta_in.setBackgroundColor(Color.parseColor("#988CB1"));
            intent.setBackgroundColor(Color.parseColor("#ECE7EE"));
            we_ta_in.setTextColor(Color.WHITE);
            intent.setTextColor(Color.BLACK);
            gridView.setVisibility(View.VISIBLE);
            gridView2.setVisibility(View.INVISIBLE);
            search.setText("");
        });

        intent.setOnClickListener(view -> {
            we_ta_in.setBackgroundColor(Color.parseColor("#ECE7EE"));
            intent.setBackgroundColor(Color.parseColor("#988CB1"));
            intent.setTextColor(Color.WHITE);
            we_ta_in.setTextColor(Color.BLACK);
            gridView.setVisibility(View.INVISIBLE);
            gridView2.setVisibility(View.VISIBLE);
            search.setText("");
        });

        //스피너
        Spinner s1 = v.findViewById(R.id.spinner1);
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Spinner s2 = v.findViewById(R.id.spinner2);
        s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        return v;
    }

    private void hideKeyboard() {
        if (getActivity() != null && getActivity().getCurrentFocus() != null) {
            InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}