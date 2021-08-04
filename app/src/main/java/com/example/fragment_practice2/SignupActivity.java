package com.example.fragment_practice2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;



import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;

public class SignupActivity extends AppCompatActivity {
    LinearLayout linear_signup, linear_signup_border, linear_signup_id, linear_signup_email,
            linear_signup_nickname;
    EditText edit_signup_id, edit_signup_pwd, edit_signup_repwd,
            edit_signup_email,edit_signup_nickname;
    Button btn_signup_checkdialog,
            btn_signup_back2login;

    FirebaseAuth auth;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Toolbar toolbar=findViewById(R.id.toolbar_layout);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Register");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        linear_signup = findViewById(R.id.linear_signup);
        linear_signup_border = findViewById(R.id.linear_signup_border);
        linear_signup_id = findViewById(R.id.linear_signup_id);
        linear_signup_email = findViewById(R.id.linear_signup_email);
        linear_signup_nickname = findViewById(R.id.linear_signup_nickname);

        edit_signup_nickname = findViewById(R.id.edit_signup_nickname);
        edit_signup_repwd = findViewById(R.id.edit_signup_repwd);



        //로그인 화면으로 돌아가기 버튼 눌렀을 때
        btn_signup_back2login = findViewById(R.id.btn_signup_back2login);
        btn_signup_back2login.setOnClickListener(view -> finish());

        //nickname(userid용), pw, email로만 인증
        edit_signup_nickname = findViewById(R.id.edit_signup_nickname);
        edit_signup_pwd = findViewById(R.id.edit_signup_pwd);
        edit_signup_email = findViewById(R.id.edit_signup_email);

        auth=FirebaseAuth.getInstance();

        //회원가입 버튼 눌렀을 때
        btn_signup_checkdialog = (Button) findViewById(R.id.btn_signup_checkdialog);

        btn_signup_checkdialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_username=edit_signup_nickname.getText().toString();
                String txt_email=edit_signup_email.getText().toString();
                String txt_password=edit_signup_pwd.getText().toString();

                if(TextUtils.isEmpty(txt_username)||TextUtils.isEmpty(txt_email)||TextUtils.isEmpty(txt_password)){
                    Toast.makeText(SignupActivity.this,"모두 채워 주세요",Toast.LENGTH_SHORT).show();
                }
                else if(txt_password.length()<6){
                    Toast.makeText(SignupActivity.this,"패스워드 길이는 최소 6글자 이상입니다.",Toast.LENGTH_SHORT).show();
                }
                else {
                    register(txt_username,txt_email,txt_password);
                }
            }
        });


    }

    private void register(String username, String email, String password){
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
              if(task.isSuccessful()){
                  FirebaseUser firebaseUser=auth.getCurrentUser();
                  assert firebaseUser != null;
                  String userid=firebaseUser.getUid();
                  //String username;

                  reference= FirebaseDatabase.getInstance().getReference("Users").child(userid);

                  HashMap<String, String>hashMap=new HashMap<>();
                  hashMap.put("id",userid);
                  hashMap.put("username",username);
                  hashMap.put("imageURL","defalt" );

                  reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                      @Override
                      public void onComplete(@NonNull Task<Void> task) {
                          if(task.isSuccessful()){
                              Intent intent=new Intent(SignupActivity.this,LoginActivity.class);
                              intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                              startActivity(intent);
                              finish();
                          }
                      }
                  });
              }
              else {
                  Toast.makeText(SignupActivity.this,"이메일 혹은 패스워트 등록에 실패하였습니다.",Toast.LENGTH_SHORT).show();

              }
            }
        });

    }
/*
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

 */
}