package com.example.main.view;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.main.view.adapter.StartUp;

import java.util.List;

public class StartupViewModel extends ViewModel {
    private StartupRepository repository;
    private MutableLiveData<List<StartUp>> startupsLiveData;
    private final MutableLiveData<String> selectedCategory = new MutableLiveData<>();
    private final MutableLiveData<List<StartUp>> filteredStartupsLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> searchQueryLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<StartUp>> searchResultsLiveData = new MutableLiveData<>();
    String categoryName;
    String searchQuery;


    public StartupViewModel() {

        repository = new StartupRepository();
        startupsLiveData = repository.getAllStartups();
        Log.d("DebugTag", "Filtered Startups : StartupViewModel Constructor: " + categoryName);

    }

    private final LiveData<List<StartUp>> filteredStartupLiveData = Transformations.switchMap(selectedCategory, category -> {
        if (category != null) {
            return repository.fetchFilteredStartups(category);
        }
        return new MutableLiveData<>(); // Return an empty LiveData if category is null
    });

    public void setSelectedCategory(String category) {
        selectedCategory.setValue(category);
    }

    public void setQuery(String query) {
        searchQueryLiveData.setValue(searchQuery);
    }

    public LiveData<List<StartUp>> getStartups() {
        if (startupsLiveData == null) {
            startupsLiveData = repository.getAllStartups();
        }
        return startupsLiveData;
    }

    public LiveData<List<StartUp>> getFilteredStartupsLiveData() {

        return filteredStartupLiveData;

    }

    public void setSearchQuery(String query) {
        searchQueryLiveData.setValue(query);
    }

    public LiveData<List<StartUp>> getSearchResultsLiveData() {
       return searchLiveData;
    }

    private final LiveData<List<StartUp>> searchLiveData = Transformations.switchMap(searchQueryLiveData, query -> {
        if (!query.isEmpty()) {
            return repository.fetchStartupsThroughSearch(query);
        }
        return new MutableLiveData<>(); // Return an empty LiveData if category is null
    });




}
