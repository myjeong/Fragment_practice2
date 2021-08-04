package com.example.fragment_practice2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        final ImageView imageView = findViewById(R.id.cloud1);
        imageView.setVisibility(View.INVISIBLE);

        final ImageView imageView2 = findViewById(R.id.cloud2);
        imageView2.setVisibility(View.INVISIBLE);

        final ImageView imageView3 = findViewById(R.id.we_ta);
        imageView2.setVisibility(View.INVISIBLE);


        Handler handler = new Handler();
        handler.postDelayed(() -> {
            imageView.setVisibility(View.VISIBLE);
            imageView2.setVisibility(View.VISIBLE);
            imageView3.setVisibility(View.VISIBLE);
        }, 500);


        handler.postDelayed(() -> {
            Intent intent = new Intent(StartActivity.this.getApplicationContext(), LoginActivity.class);
            StartActivity.this.startActivity(intent);
        }, 2000);
    }
}