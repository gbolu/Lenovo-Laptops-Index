package com.example.lenovolaptopsproj.Pages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.lenovolaptopsproj.R;
import com.example.lenovolaptopsproj.Adapters.LaptopAdapter;
import com.example.lenovolaptopsproj.Controllers.ProductController;
import com.example.lenovolaptopsproj.Models.ProductModel;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {
    private ProductController dbHandler;
    ArrayList<ProductModel> arrayList1;
    RecyclerView recyclerView1;
    String[] service = {"Android App Developer", "Web Developer", "Android App Developer", "Web Developer", "Android App Developer", "Web Developer"};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        recyclerView1 = findViewById(R.id.recycler_view1);
        arrayList1 = new ArrayList<>();
        addServices();
    }

    public void addServices()
    {
        recyclerView1.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView1.setItemAnimator(new DefaultItemAnimator());
        for (String s : service)
        {
            ProductModel productModel = new ProductModel();
            productModel.setLaptop_name(s);
            arrayList1.add(productModel);
        }

        LaptopAdapter adapter = new LaptopAdapter(getApplicationContext(), arrayList1);
        recyclerView1.setAdapter(adapter);
    }
}