package com.example.lenovolaptopsproj.Pages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
                Intent n_int = new Intent(getApplicationContext(), SearchActivity.class);
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
    }
}