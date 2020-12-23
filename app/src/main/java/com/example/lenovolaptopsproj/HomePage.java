package com.example.lenovolaptopsproj;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {

    ArrayList<itemModel> arrayList1;
    RecyclerView recyclerView1;
    String service[] = {"Android App Developer", "Web Developer", "Android App Developer", "Web Developer", "Android App Developer", "Web Developer"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        recyclerView1 = (RecyclerView) findViewById(R.id.recycler_view1);
        arrayList1 = new ArrayList<>();

        addServices();

        ImageView yoga = findViewById(R.id.yogalogo);
        ImageView lenovo = findViewById(R.id.lenovologo);
        ImageView thinkbook = findViewById(R.id.thinkBooklogo);
        ImageView thinkpad = findViewById(R.id.thinklogo);
        ImageView legion = findViewById(R.id.legionlogo);
        ImageView ideapad = findViewById(R.id.ideapadlogo);

        yoga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent yoga = new Intent(getBaseContext(), YogaPage.class);
                startActivity(yoga);
            }
        });
    }

    public void addServices() {
        recyclerView1.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView1.setItemAnimator(new DefaultItemAnimator());
        for (int i = 0; i < service.length; i++) {
            itemModel itemModel = new itemModel();
            itemModel.setService(service[i]);
            arrayList1.add(itemModel);
        }

        ServicesAdapter adapter = new ServicesAdapter(getApplicationContext(), arrayList1);
        recyclerView1.setAdapter(adapter);
    }
}