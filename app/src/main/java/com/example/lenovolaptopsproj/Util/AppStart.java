package com.example.lenovolaptopsproj.Util;

import android.app.Application;

import com.example.lenovolaptopsproj.Controllers.BrandController;
import com.example.lenovolaptopsproj.Controllers.ProductController;

public class AppStart extends Application
{
    @Override
    public void onCreate() {
        ProductController.generateSeedProducts(getApplicationContext(), "products.json");
        BrandController.generateSeedBrands(getApplicationContext(), "brands.json");
        super.onCreate();
    }
}
