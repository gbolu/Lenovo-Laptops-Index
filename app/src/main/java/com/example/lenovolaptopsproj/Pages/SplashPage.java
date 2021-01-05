package com.example.lenovolaptopsproj.Pages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.lenovolaptopsproj.Controllers.ProductController;
import com.example.lenovolaptopsproj.R;

public class SplashPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread background = new Thread() {
            public void run() {
            try {
                // Thread will sleep for 2 seconds
                sleep(2000);

                // After 2 seconds redirect to app homepage
                Intent i=new Intent(getBaseContext(), HomePage.class);
                startActivity(i);

                //Remove current activity
                finish();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            }
        };

        background.start();
    }
}