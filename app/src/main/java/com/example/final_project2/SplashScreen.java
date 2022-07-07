package com.example.final_project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

public class SplashScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
       final boolean b = new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
               Intent  i = new Intent(getApplicationContext(),MainActivity.class) ;
               startActivity(i);
                finish();
            }
       }, 4000);

}}