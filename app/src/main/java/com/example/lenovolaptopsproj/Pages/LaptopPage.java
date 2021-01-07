package com.example.lenovolaptopsproj.Pages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovolaptopsproj.Adapters.LaptopAdapter;
import com.example.lenovolaptopsproj.Adapters.SpecAdapter;
import com.example.lenovolaptopsproj.Controllers.ProductController;
import com.example.lenovolaptopsproj.Models.ProductModel;
import com.example.lenovolaptopsproj.R;

import java.util.ArrayList;
import java.util.Objects;

public class LaptopPage extends AppCompatActivity {
    TextView laptopName;
    TextView laptopDetails;

    RecyclerView laptopSpecsView;
    RecyclerView similarLaptopsView;

    ImageView laptopImage;

    ProductModel laptop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laptop_sample_page);

        laptop = ProductController.getProduct(Objects.requireNonNull(getIntent().getExtras()).getInt("laptopID"));

        laptopName = findViewById(R.id.laptop_title);
        laptopName.setText(laptop.getLaptop_name());

        laptopDetails = findViewById(R.id.laptop_details);
        laptopDetails.setText(laptop.getLaptop_description());

        setLaptopSpecs();
        setSimilarLaptops();

        laptopImage = findViewById(R.id.laptop_image);
        laptopImage.setImageURI(Uri.parse(
                "android.resource://" + getApplicationContext().getPackageName() +
                        "/drawable/" + laptop.getLaptop_imageRef()));
    }

    public void setLaptopSpecs()
    {
        laptopSpecsView = findViewById(R.id.laptops_specs);
        laptopSpecsView.setAdapter(new SpecAdapter(getApplicationContext(), laptop.getSpecs()));
        laptopSpecsView.setItemAnimator(new DefaultItemAnimator());
        laptopSpecsView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        laptopSpecsView.setFocusable(false);
    }

    public void setSimilarLaptops()
    {
        similarLaptopsView = findViewById(R.id.similar_laptops);
        ArrayList<ProductModel> similarLaptops = new ArrayList<>(ProductController.searchProducts(laptop.getLaptop_brand()));

        //  remove current laptop from similarLaptops
        int index = 0;
        while(index < similarLaptops.size()){
            if(similarLaptops.get(index).getLaptop_name().equalsIgnoreCase(laptop.getLaptop_name())){
                similarLaptops.remove(index);
                break;
            }
            index++;
        }

        similarLaptopsView.setAdapter(new LaptopAdapter(getApplicationContext(), similarLaptops));
        similarLaptopsView.setItemAnimator(new DefaultItemAnimator());
        similarLaptopsView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        similarLaptopsView.setFocusable(false);
    }
}