package com.example.main.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.main.R;
import com.example.main.view.adapter.StartUp;
import com.example.main.view.adapter.StartupAdapter;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
//    Search View
    private SearchView searchView;
// Recycler View
    private RecyclerView recyclerView;
    private StartupAdapter adapter;
    private StartupViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchView = findViewById(R.id.searchView);
        recyclerView = findViewById(R.id.recyclerView_SA);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        searchView.setFocusable(true);
        searchView.setIconified(false);
        searchView.requestFocus();
        ViewModelProvider.Factory factory = new ViewModelProvider.NewInstanceFactory();
        viewModel = new ViewModelProvider(this, factory).get(StartupViewModel.class);


        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    searchView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            searchView.requestFocusFromTouch();
                        }
                    }, 200); // Delay to ensure keyboard opens
                }
            }
        });
        adapter = new StartupAdapter(new ArrayList<>(), this);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Call ViewModel's setSearchQuery to send the search query
                viewModel.setSearchQuery(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Call ViewModel's setSearchQuery to send the updated search query
                viewModel.setSearchQuery(newText);

                return true;
            }
        });
        viewModel.getSearchResultsLiveData().observe(this, new Observer<List<StartUp>>() {
            @Override
            public void onChanged(List<StartUp> startups) {
                adapter.setStartups(startups);
            }
        });
        recyclerView.setAdapter(adapter);



    }
}