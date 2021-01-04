package com.example.lenovolaptopsproj.Controllers;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.example.lenovolaptopsproj.Models.ProductModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public final class ProductController
{
    static ArrayList<ProductModel> allProducts = new ArrayList<>();
    
    public static ArrayList<ProductModel> getAllProducts() {
        return allProducts;
    }

    public final ProductModel getProduct(int productIndex)
    {
        return allProducts.get(productIndex);
    }

    //  convert products JSON file to a JSON string
    private static String toJSONString(Context context, String filename)
    {
        StringBuilder str = new StringBuilder();
        try
        {
            AssetManager assetManager = context.getAssets();
            InputStream in = assetManager.open(filename);
            InputStreamReader isr = new InputStreamReader(in);
            char [] inputBuffer = new char[100];

            int charRead;
            while((charRead = isr.read(inputBuffer))>0)
            {
                String readString = String.copyValueOf(inputBuffer,0,charRead);
                str.append(readString);
            }
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }

        return str.toString();
    }

    //  add all products to be used in the application
    public static void generateSeedProducts(final Context ctx, String filename)
    {
        String productsJSONStr = toJSONString(ctx, filename);
        try
        {
            JSONObject productsJSONObj = new JSONObject(productsJSONStr);
            JSONArray laptops = productsJSONObj.getJSONArray("laptops");

            //  loop through all products
            for (int index = 0; index < laptops.length(); index++)
            {
                JSONObject product = laptops.getJSONObject(index);

                ProductModel temp = new ProductModel();
                temp.setLaptop_name(product.getString("name"));
                temp.setLaptop_brand(product.getString("brand"));
                temp.setLaptop_sub_brand(product.getString("subBrand"));
                temp.setLaptop_description(product.getString("description"));
                temp.setLaptop_imageRef(product.getString("imageRef"));
                temp.setHasMultipleProcessors(product.getBoolean("hasMultipleProcessors"));

                allProducts.add(temp);
            }
        }

        catch (final JSONException e)
        {
            Log.e(ProductController.class.getSimpleName(), "Json parsing error: " + e.getMessage());
            new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(ctx,
                        "Json parsing error: " + e.getMessage(),
                        Toast.LENGTH_LONG).show();
                }
            };
        }
    }

    //  search list of products for items
    public static ArrayList<ProductModel> searchProducts(String searchString)
    {
        String[] strings_search = searchString.split("\\s+");

        for (int i = 0; i < strings_search.length; i++)
        {
            strings_search[i] = strings_search[i].replaceFirst(
                    String.valueOf(strings_search[i].charAt(0)),
                    String.valueOf(Character.toUpperCase(strings_search[i].charAt(0))));
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            searchString = String.join(" ", strings_search).trim();
        } else {
            searchString = TextUtils.join(" ", strings_search);
        }

        ArrayList<ProductModel> searchResults = new ArrayList<>();
        int index = 0;
        while(index < allProducts.size())
        {
            ProductModel tempProduct = allProducts.get(index);

            if(tempProduct.getLaptop_name().contains(searchString)
                    || tempProduct.getLaptop_brand().contains(searchString)
                    || tempProduct.getLaptop_sub_brand().contains(searchString))
            {
                searchResults.add(tempProduct);
            }

            index++;
        }

        return searchResults;
    }

    // grab a random sample of products for display on homepage
    public static ArrayList<ProductModel> getRandomProducts(int limit)
    {
        int index = 0;
        ArrayList<Integer> used_rands = new ArrayList<>();
        ArrayList<ProductModel> randomProducts = new ArrayList<>();

        while(index < limit)
        {
            int random_int = (int)(Math.random() * (allProducts.size()));

            if(!used_rands.contains(random_int))
            {
                randomProducts.add(allProducts.get(random_int));
                used_rands.add(random_int);
                index++;
            }
        }

        return randomProducts;
    }
}
