package com.example.fragment_practice2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fragment_practice2.findPwd.FindIdPwdActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.rengwuxian.materialedittext.MaterialEditText;

public class LoginActivity extends AppCompatActivity {

    ////여기서 user_id: email 주소
    EditText user_id, password;
    Button login, signUp;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth=FirebaseAuth.getInstance();

        //여기서 id: email 주소
        user_id=findViewById(R.id.id);
        password = findViewById(R.id.pwd);
        login = findViewById(R.id.login);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_email=user_id.getText().toString();
                String txt_password=password.getText().toString();

                if(TextUtils.isEmpty(txt_email)||TextUtils.isEmpty(txt_password)){
                    Toast.makeText(LoginActivity.this,"모든 칸 채우기",Toast.LENGTH_SHORT).show();
                }
                else {
                    auth.signInWithEmailAndPassword(txt_email,txt_password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        Intent intent=new Intent(LoginActivity.this, activity_bottomNavigation.class);
                                        //이전에 실행한 상위 액티비티를 모두 종류하고 새로운 태스크를 생성한다.
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else{
                                        Toast.makeText(LoginActivity.this,"Authentication failed",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

        signUp = findViewById(R.id.signUp);
        signUp.setOnClickListener(view -> {
            //회원 가입 버튼 누른 후 회원가입 페이지로 이동
            Intent intent_2signup = new Intent(LoginActivity.this, SignupActivity.class);
            startActivity(intent_2signup);
        });

        TextView this_click = findViewById(R.id.this_click);  //"여기" 글자
        Spannable span = (Spannable) this_click.getText();
        span.setSpan(new UnderlineSpan(), 0, 2, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        this_click.setMovementMethod(LinkMovementMethod.getInstance());
        span.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Intent intent = new Intent(LoginActivity.this.getApplicationContext(), FindIdPwdActivity.class);
                LoginActivity.this.startActivity(intent);
            }
        }, 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        this_click.setMovementMethod(LinkMovementMethod.getInstance());
        span.setSpan(new ForegroundColorSpan(Color.rgb(102, 78, 152)), 0, 2, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        editText1 = findViewById(R.id.editText1);
//        linearLayout = findViewById(R.id.linear);
        View view = getCurrentFocus();
        if (view != null && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) && view instanceof EditText && !view.getClass().getName().startsWith("android.webkit.")) {
            int[] scrooges = new int[2];
            view.getLocationOnScreen(scrooges);
            float x = ev.getRawX() + view.getLeft() - scrooges[0];
            float y = ev.getRawY() + view.getTop() - scrooges[1];
            if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom())
                ((InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow((this.getWindow().getDecorView().getApplicationWindowToken()), 0);

//            String s1 = editText1.getText().toString();
//            if (s1.length() == 0) {
//                textView.setVisibility(View.INVISIBLE);
//                linearLayout.setBackgroundResource(R.drawable.edit_radius);
//            }
        }
        return super.dispatchTouchEvent(ev);
    }
}