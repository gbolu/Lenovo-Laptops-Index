package com.example.lenovolaptopsproj.Util;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.widget.SearchView;

import com.example.lenovolaptopsproj.Pages.SearchPage;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Helpers
{
    //  convert products JSON file to a JSON string
    public static String toJSONString(Context context, String filename)
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
}
