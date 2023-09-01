package com.example.seedstake;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(() -> {
//                getActionBar().hide();
            startActivity(new Intent(MainActivity.this,com.example.auth.view.LoginActivity.class));
            finish();
        },3000);
    }
}