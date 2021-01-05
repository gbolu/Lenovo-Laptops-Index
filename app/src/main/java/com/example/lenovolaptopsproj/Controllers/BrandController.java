package com.example.lenovolaptopsproj.Controllers;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.lenovolaptopsproj.Models.BrandModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.lenovolaptopsproj.Util.Helpers.toJSONString;

public final class BrandController
{
    static ArrayList<BrandModel> allBrands = new ArrayList<>();

    public static ArrayList<BrandModel> getAllBrands() {
        return allBrands;
    }

    public final static BrandModel getBrand(int brandIndex)
    {
        return allBrands.get(brandIndex);
    }

    //  add all brands to be used in the application
    public static void generateSeedBrands(final Context ctx, String filename)
    {
        String productsJSONStr = toJSONString(ctx, filename);
        try
        {
            JSONObject productsJSONObj = new JSONObject(productsJSONStr);
            JSONArray laptops = productsJSONObj.getJSONArray("brands");

            //  loop through all products
            for (int index = 0; index < laptops.length(); index++)
            {
                JSONObject product = laptops.getJSONObject(index);
                BrandModel temp = new BrandModel();

                temp.setBrandName(product.getString("name"));

                JSONArray subBrandArr = product.getJSONArray("subBrands");
                String[] tempArr = new String[subBrandArr.length()];
                for (int i = 0; i < subBrandArr.length(); i++) {
                    tempArr[i] = subBrandArr.getString(i);
                }
                temp.setSubBrands(tempArr);

                temp.setDetails(product.getString("details"));

                temp.setVideoRef(product.getString("videoRef"));
                allBrands.add(temp);
            }
        }

        catch (final JSONException e)
        {
            Log.e(com.example.lenovolaptopsproj.Controllers.ProductController.class.getSimpleName(), "Json parsing error: " + e.getMessage());
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
}
