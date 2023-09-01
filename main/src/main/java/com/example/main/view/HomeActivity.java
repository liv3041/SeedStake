package com.example.main.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.main.R;
import com.example.main.view.adapter.Category;
import com.example.main.view.adapter.CategoryGVAdapter;
import com.example.main.view.adapter.StartUp;
import com.example.main.view.adapter.StartupAdapter;
import com.example.main.view.adapter.ZeroSpacingItemDecoration;
import com.example.main.view.adapter.OnCategoryClickListener;


import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends AppCompatActivity implements OnItemClickListener,OnCategoryClickListener{
//    GridView gridView;

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    public String TAG ="HomeActivity";

    private StartupViewModel viewModel;
    private StartupAdapter adapter;

    //    Category
    private final List<Category> categoryList = new ArrayList<>();
    private RecyclerView categoryRecyclerView;

    //    SearchView
    SearchView searchView;
    private CategoryGVAdapter categoryAdapter;

//    Frame loyout for opening search box
   private FrameLayout frameLayout;

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        categoryRecyclerView = findViewById(R.id.categoryRecyclerView);
        recyclerView = findViewById(R.id.recyclerView);
        frameLayout = findViewById(R.id.customBox);
        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });


        ViewModelProvider.Factory factory = new ViewModelProvider.NewInstanceFactory();
        viewModel = new ViewModelProvider(this, factory).get(StartupViewModel.class);

        viewModel = ViewModelProviders.of(this).get(StartupViewModel.class);

        setCategory();
        createList();
        // Set up your Category Adapter's categoryClickListener
        adapter = new StartupAdapter(new ArrayList<>(), this);
        categoryAdapter.setCategoryClickListener(this::onCategoryClick);
        LiveData<List<StartUp>> filteredStartupsLiveData = viewModel.getFilteredStartupsLiveData();
        filteredStartupsLiveData.observe(this, startups -> {

             adapter.setStartups(startups);

        });
        viewModel.getStartups().observe(this,startUps -> {
            adapter.setStartups(startUps);
        });
        adapter = new StartupAdapter(new ArrayList<>(), this);
        recyclerView.setAdapter(adapter);




    }



    private void setCategory() {
//        categoryRecyclerView.addItemDecoration(new DividerItemDecoration(HomeActivity.this,LinearLayoutManager.HORIZONTAL));
        ZeroSpacingItemDecoration zeroSpacingItemDecoration = new ZeroSpacingItemDecoration();
        categoryRecyclerView.addItemDecoration(zeroSpacingItemDecoration);

        categoryAdapter = new CategoryGVAdapter(categoryList, getApplicationContext(),this::onCategoryClick, viewModel);
//        categoryGVAdapter.setCategoryClickListener(this);
        LinearLayoutManager horizontalLayoutManager
                = new LinearLayoutManager(HomeActivity.this,LinearLayoutManager.HORIZONTAL,false);
        categoryRecyclerView.setLayoutManager(horizontalLayoutManager);
        categoryRecyclerView.setAdapter(categoryAdapter);
        populateCategoryList();
    }

    private void populateCategoryList() {
        Category tech = new Category(R.drawable.tech,"Tech");
        Category health = new Category(R.drawable.healthcare,"Health");
        Category fintech = new Category(R.drawable.fintech,"Fintech");
        Category food = new Category(R.drawable.food,"Food");
        Category hospitality = new Category(R.drawable.hospitality,"Hospitality");
        Category transport = new Category(R.drawable.transport,"Travel");
        Category all = new Category(R.drawable.list,"All");
        categoryList.add(all);
        categoryList.add(tech);
        categoryList.add(health);
        categoryList.add(fintech);
        categoryList.add(food);
        categoryList.add(hospitality);
        categoryList.add(transport);


    }

    private void createList() {

//        StartupAdapter startupAdapter = new StartupAdapter(new ArrayList<>(), this);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
//        adapter.setItemClickListener(this);


    }

    @Override
    public void onClick(View view, int position) {
        List<StartUp> startups = viewModel.getStartups().getValue();
        if (startups != null && position < startups.size()) {
            StartUp clickedStartup = startups.get(position);
            Intent intent = new Intent(this, StartUpProfileActivity.class);
            intent.putExtra("startup", clickedStartup);
            startActivity(intent);
        }
    }

public void onCategoryClick(Category category) {
    String selectedCategory = category.getCategoryName();

    viewModel.getFilteredStartupsLiveData();
    viewModel.setSelectedCategory(selectedCategory);

    // Observe the filtered startups LiveData using the public method
    LiveData<List<StartUp>> filteredLiveData = viewModel.getFilteredStartupsLiveData();

    // Create a mutable copy of the LiveData
    MutableLiveData<List<StartUp>> mutableLiveData = new MutableLiveData<>();
    filteredLiveData.observe(this, mutableLiveData::setValue);

    // Update the adapter with the mutableLiveData
    mutableLiveData.observe(this, startups -> {
        adapter.setStartups(startups);
    });

}
}