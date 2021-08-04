package com.example.fragment_practice2.findPwd;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fragment_practice2.LoginActivity;
import com.example.fragment_practice2.R;

public class FindIdPwdActivity extends AppCompatActivity {

    FrameLayout frame_find_id, frame_find_id_ok, frame_find_pwd;
    TextView text_find_pwd, text_find_id_email;
    Button btn_find_id, btn_find_pwd, btn_set_pwd;
    EditText edit_email, edit_cer_num, edit_new_pwd, edit_new_pwd_check;
    boolean bool_check_btn = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_id_pwd);

        frame_find_id = findViewById(R.id.frame_find_id);
        frame_find_id_ok = findViewById(R.id.frame_find_id_ok);
        frame_find_id_ok.setVisibility(View.INVISIBLE);
        frame_find_pwd = findViewById(R.id.frame_find_pwd);
        frame_find_pwd.setVisibility(View.INVISIBLE);

        edit_email = findViewById(R.id.edit_email);
        edit_cer_num = findViewById(R.id.edit_cer_num);

        edit_new_pwd = findViewById(R.id.edit_new_pwd);
        edit_new_pwd_check = findViewById(R.id.edit_new_pwd_check);

        text_find_id_email = findViewById(R.id.text_find_id_email);
        Spannable span = (Spannable) text_find_id_email.getText();
        span.setSpan(new ForegroundColorSpan(Color.rgb(102, 78, 152)), 5, 8, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

        btn_find_id = findViewById(R.id.btn_find_id);  //아이디 찾기
        btn_find_id.setOnClickListener(v -> {
            bool_check_btn = true;
            frame_find_id.setVisibility(View.VISIBLE);
            frame_find_id_ok.setVisibility(View.INVISIBLE);
            frame_find_pwd.setVisibility(View.INVISIBLE);
            btn_find_id.setBackgroundColor(Color.parseColor("#C6B7CB"));
            btn_find_pwd.setBackgroundColor(Color.parseColor("#DDD5E0"));
            edit_email.setText("");
            edit_cer_num.setText("");
            text_find_id_email.setText("찾으려는 아이디의 이메일을 입력하세오.");
            Spannable span1 = (Spannable) text_find_id_email.getText();
            span1.setSpan(new ForegroundColorSpan(Color.rgb(102, 78, 152)), 5, 8, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        });

        btn_find_pwd = findViewById(R.id.btn_find_pwd);  //비밀번호 찾기
        btn_find_pwd.setOnClickListener(v -> {
            bool_check_btn = false;
            frame_find_id.setVisibility(View.VISIBLE);
            frame_find_id_ok.setVisibility(View.INVISIBLE);
            frame_find_pwd.setVisibility(View.INVISIBLE);
            btn_find_id.setBackgroundColor(Color.parseColor("#DDD5E0"));
            btn_find_pwd.setBackgroundColor(Color.parseColor("#C6B7CB"));
            edit_email.setText("");
            edit_cer_num.setText("");
            edit_new_pwd.setText("");
            edit_new_pwd_check.setText("");
            text_find_id_email.setText("찾으려는 비밀번호의 이메일을 입력하세오.");
            Spannable span2 = (Spannable) text_find_id_email.getText();
            span2.setSpan(new ForegroundColorSpan(Color.rgb(102, 78, 152)), 5, 9, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        });


        Button btn_id_check = findViewById(R.id.btn_check);
        btn_id_check.setOnClickListener(v -> {
            if (bool_check_btn) {  //아이디 찾기 id 확인
                frame_find_id.setVisibility(View.INVISIBLE);
                frame_find_id_ok.setVisibility(View.VISIBLE);
            } else {
                frame_find_id.setVisibility(View.INVISIBLE);
                frame_find_id_ok.setVisibility(View.INVISIBLE);
                frame_find_pwd.setVisibility(View.VISIBLE);
            }
        });

        text_find_pwd = findViewById(R.id.text_find_pwd);  //비밀번호 찾기로 넘어가기
        text_find_pwd.setOnClickListener(v -> {
            bool_check_btn = false;
            frame_find_id.setVisibility(View.VISIBLE);
            frame_find_id_ok.setVisibility(View.INVISIBLE);
            frame_find_pwd.setVisibility(View.INVISIBLE);
            btn_find_id.setBackgroundColor(Color.parseColor("#DDD5E0"));
            btn_find_pwd.setBackgroundColor(Color.parseColor("#C6B7CB"));
            edit_email.setText("");
            edit_cer_num.setText("");
            edit_new_pwd.setText("");
            edit_new_pwd_check.setText("");
            text_find_id_email.setText("찾으려는 비밀번호의 이메일을 입력하세오.");
            Spannable span3 = (Spannable) text_find_id_email.getText();
            span3.setSpan(new ForegroundColorSpan(Color.rgb(102, 78, 152)), 5, 9, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        });

        btn_set_pwd = findViewById(R.id.btn_set_pwd);
        btn_set_pwd.setOnClickListener(view -> {
            edit_new_pwd.setText("");
            edit_new_pwd_check.setText("");
            final Dialog dialog = new Dialog(FindIdPwdActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_pwd_ok);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
            Button button = dialog.findViewById(R.id.btn_ok);
            button.setOnClickListener(v -> dialog.dismiss());
        });

        Button back_login = findViewById(R.id.back_login);
        back_login.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        });

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View view = getCurrentFocus();
        if (view != null && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) && view instanceof EditText && !view.getClass().getName().startsWith("android.webkit.")) {
            int[] scrooges = new int[2];
            view.getLocationOnScreen(scrooges);
            float x = ev.getRawX() + view.getLeft() - scrooges[0];
            float y = ev.getRawY() + view.getTop() - scrooges[1];
            if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom())
                ((InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow((this.getWindow().getDecorView().getApplicationWindowToken()), 0);
        }
        return super.dispatchTouchEvent(ev);
    }
}
