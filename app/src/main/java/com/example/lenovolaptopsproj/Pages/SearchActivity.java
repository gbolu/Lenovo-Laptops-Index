package com.example.lenovolaptopsproj.Pages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lenovolaptopsproj.Adapters.SearchAdapter;
import com.example.lenovolaptopsproj.Controllers.ProductController;
import com.example.lenovolaptopsproj.Models.ProductModel;
import com.example.lenovolaptopsproj.R;
import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    ArrayList<ProductModel> searchResults;
    RecyclerView searchResultsView;
    SearchAdapter searchAdapter;
    TextView noSearchResults;
    LinearLayout noSearchLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchResults = new ArrayList<>();
        searchAdapter = new SearchAdapter(getBaseContext(), searchResults);

        searchResultsView = findViewById(R.id.search_results);
        noSearchResults = findViewById(R.id.no_search_results);
        noSearchLinearLayout = findViewById(R.id.no_search_layout);
        searchResultsView.setAdapter(searchAdapter);
        searchResultsView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        searchResultsView.setItemAnimator(new DefaultItemAnimator());

        // Get the intent, verify the action and get the query
        handleIntent(getIntent());
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            final String query = intent.getStringExtra(SearchManager.QUERY);
            new Thread(){
                @Override
                public void run() {
                    doMySearch(query);
                }
            }.start();
        }
    }

    private void doMySearch(final String query) {
        Log.i("Search Query: ", query);
        ArrayList<ProductModel> newSearchResults;
        newSearchResults = ProductController.searchProducts(query);

        if(newSearchResults.size() != 0)
        {
            noSearchLinearLayout.setVisibility(View.GONE);
            searchResults.clear();
            searchResults.addAll(newSearchResults);
            searchAdapter.notifyDataSetChanged();
        }
        else {
            searchResultsView.setVisibility(View.GONE);
            noSearchResults.setText("No search results");
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        handleIntent(intent);
    }
}