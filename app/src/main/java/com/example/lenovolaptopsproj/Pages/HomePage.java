package com.example.lenovolaptopsproj.Pages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;

import com.example.lenovolaptopsproj.R;
import com.example.lenovolaptopsproj.Adapters.LaptopAdapter;
import com.example.lenovolaptopsproj.Controllers.ProductController;
import com.example.lenovolaptopsproj.Models.ProductModel;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {
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

        searchBtn.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Intent n_int = new Intent(getApplicationContext(), SearchPage.class);
                n_int.setAction(Intent.ACTION_SEARCH);
                n_int.putExtra(SearchManager.QUERY, s);
                startActivity(n_int);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        recyclerView1.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView1.setItemAnimator(new DefaultItemAnimator());

        LaptopAdapter adapter = new LaptopAdapter(getApplicationContext(), products);
        recyclerView1.setAdapter(adapter);

        thinkPad = findViewById(R.id.thinkPadlogo);
        thinkBook = findViewById(R.id.thinkBooklogo);
        ideapad = findViewById(R.id.ideapad);
        legion = findViewById(R.id.legionlogo);
        lenovo = findViewById(R.id.lenovologo);
        yoga = findViewById(R.id.yogalogo);

        ArrayList<View> views = new ArrayList<>();
        views.add(yoga);
        views.add(lenovo);
        views.add(thinkBook);
        views.add(thinkPad);
        views.add(legion);
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