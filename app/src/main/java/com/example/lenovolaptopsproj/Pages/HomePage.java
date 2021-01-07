package com.example.lenovolaptopsproj.Pages;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lenovolaptopsproj.Adapters.LaptopAdapter;
import com.example.lenovolaptopsproj.Controllers.ProductController;
import com.example.lenovolaptopsproj.Models.ProductModel;
import com.example.lenovolaptopsproj.R;

import java.util.ArrayList;

public class HomePage extends SearchableActivity {
    ArrayList<ProductModel> products;
    RecyclerView recyclerView1;
    SearchView searchBtn;

    ImageView thinkPad;
    ImageView yoga;
    ImageView lenovo;
    ImageView thinkBook;
    ImageView legion;
    ImageView ideapad;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        searchBtn = findViewById(R.id.search);

        recyclerView1 = findViewById(R.id.recycler_view1);

        products = ProductController.getRandomProducts(6);

        searchBtn.setOnQueryTextListener(getNewListener());

        recyclerView1.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView1.setItemAnimator(new DefaultItemAnimator());

        LaptopAdapter adapter = new LaptopAdapter(getApplicationContext(), products);
        recyclerView1.setAdapter(adapter);
        recyclerView1.setFocusable(false);

        thinkPad = findViewById(R.id.thinkPadlogo);
        thinkBook = findViewById(R.id.thinkBooklogo);
        ideapad = findViewById(R.id.ideapad);
        lenovo = findViewById(R.id.lenovologo);

        ArrayList<View> views = new ArrayList<>();
        views.add(lenovo);
        views.add(thinkBook);
        views.add(thinkPad);
        views.add(ideapad);


        for (int i = 0; i < views.size(); i++)
        {
            final int finalI = i;
            views.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent n_intent = new Intent(getApplicationContext(), BrandPage.class);
                    n_intent.putExtra("brandID", finalI);
                        startActivity(n_intent);
                }
            });
        }
    }
}