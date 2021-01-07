package com.example.lenovolaptopsproj.Pages;

import android.app.SearchManager;
import android.content.Intent;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

public class SearchableActivity extends AppCompatActivity
{
    public SearchView.OnQueryTextListener getNewListener(){
        return new SearchView.OnQueryTextListener() {
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
        };
    }
}
