package com.example.lenovolaptopsproj.Pages;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.MediaController;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lenovolaptopsproj.Adapters.LaptopAdapter;
import com.example.lenovolaptopsproj.Controllers.BrandController;
import com.example.lenovolaptopsproj.Controllers.ProductController;
import com.example.lenovolaptopsproj.Models.BrandModel;
import com.example.lenovolaptopsproj.Models.ProductModel;
import com.example.lenovolaptopsproj.R;

import java.util.ArrayList;
import java.util.Objects;

public class BrandPage extends SearchableActivity {
    private BrandModel brandModel;
    private RecyclerView brandLaptopsView;
    private ArrayList<ProductModel> brandLaptops;
    private ArrayList<ProductModel> adapterLaptops;
    LaptopAdapter brandAdapter;
    
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_sample);

        // get the target brand
        int brandID = Objects.requireNonNull(getIntent().getExtras()).getInt("brandID");
        brandModel = BrandController.getBrand(brandID);
        brandLaptops = ProductController.searchProducts(brandModel.getBrandName());
        SearchView searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(getNewListener());

        TextView brandName = findViewById(R.id.brandName);
        brandName.setText(brandModel.getBrandName());

        TextView details = findViewById(R.id.details);
        details.setText(brandModel.getDetails());

        setVideoView();
        setBrandLaptopsView();
        setSpinner();
    }
    
    private void setBrandLaptopsView()
    {
        brandLaptopsView = findViewById(R.id.brand_laptops);
        brandLaptopsView.setLayoutManager(new GridLayoutManager(this, 2));
        brandLaptopsView.setItemAnimator(new DefaultItemAnimator());

        adapterLaptops = new ArrayList<>(brandLaptops);

        brandAdapter = new LaptopAdapter(getApplicationContext(), adapterLaptops);
        brandLaptopsView.setAdapter(brandAdapter);
        brandLaptopsView.setFocusable(false);
    }
    
    private void setVideoView()
    {
        final VideoView videoView = findViewById(R.id.videoView2);
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/raw/" + brandModel.getVideoRef()));
        MediaController mc = new MediaController(this);
        mc.setAnchorView(videoView);
        mc.setMediaPlayer(videoView);
        videoView.setMediaController(mc);
        MediaPlayer.OnPreparedListener PreparedListener = new MediaPlayer.OnPreparedListener(){
            @Override
            public void onPrepared(MediaPlayer m) {
                try {
                    if (m.isPlaying()) {
                        m.stop();
                        m.release();
                        m = new MediaPlayer();
                    }
                    m.setVolume(0f, 0f);
                    m.setLooping(true);
                    m.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        videoView.setOnPreparedListener(PreparedListener);
    }
    
    private void setSpinner()
    {
        final Spinner brandSpinner = findViewById(R.id.spinner);

        // Create an ArrayAdapter using the string array and a default spinner layout
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,
                brandModel.getSubBrands());

        // Apply the adapter to the spinner
        brandSpinner.setAdapter(adapter);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        brandSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                adapterView.setSelected(true);
                String selectedSubBrand = adapterView.getSelectedItem().toString();
                Log.i("Selected Sub Brand:", selectedSubBrand);
                int index = 0;

                while (index < brandModel.getSubBrands().length)
                {
                    if(brandModel.getSubBrands()[index].equals(selectedSubBrand)){
                        ArrayList<ProductModel> selectedBrandLaptops = ProductController.searchProductsBySubBrand(selectedSubBrand);
                        adapterLaptops.clear();
                        adapterLaptops.addAll(selectedBrandLaptops);
                        brandAdapter.notifyDataSetChanged();
                        adapter.notifyDataSetChanged();
                        break;
                    }
                    index++;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
    }
}