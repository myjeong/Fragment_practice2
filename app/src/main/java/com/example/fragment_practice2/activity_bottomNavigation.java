package com.example.fragment_practice2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

//BottomNavigationView를 위한 import
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class activity_bottomNavigation extends AppCompatActivity {

    //바텀네비게이션 뷰
    private BottomNavigationView mBottomNavigationView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);

        mBottomNavigationView=findViewById(R.id.bottom_navigation);

        //첫 화면 띄우기
        //프래그먼트의 경우, commit()함수로 적용시점을 명시해야함
        getSupportFragmentManager().beginTransaction().add(R.id.frame_container,new fragment_home()).commit();


        //bottom navigationview 안의 아이템 설정
        //replace(): 기존에 생성된 프래그먼트들을 모두 onDestroy상태로 만들고 새로운 프래그먼트 1개를 화면에 보여줌
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home_item:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new fragment_home()).commit();
                        break;
                    case R.id.find_item:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new fragment_find()).commit();
                        break;
                    case R.id.write_item:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new fragment_write()).commit();
                        break;
                    case R.id.myPage_item:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new fragment_mypage()).commit();
                        break;

                }


                return true;
            }
        });

    }
}