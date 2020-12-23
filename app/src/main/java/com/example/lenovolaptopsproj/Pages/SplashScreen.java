package com.example.lenovolaptopsproj.Pages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.lenovolaptopsproj.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread background = new Thread() {
            public void run() {
            try {
                // Thread will sleep for 3 seconds
                sleep(1000);

                // After 5 seconds redirect to another intent
                Intent i=new Intent(getBaseContext(), HomePage.class);
                startActivity(i);

                //Remove activity
                finish();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            }
        };

        background.start();
    }
}