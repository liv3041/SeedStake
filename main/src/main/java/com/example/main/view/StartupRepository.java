package com.example.main.view;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.main.view.adapter.StartUp;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StartupRepository {
    private static final String TAG = "StartupRepository";
    private final StartupApiService apiService = RetrofitClient.getRetrofitInstance().create(StartupApiService.class);
    private final MutableLiveData<List<StartUp>> startupsLiveData = new MutableLiveData<>();
    private List<StartUp> startUpList = new ArrayList<>();


    public MutableLiveData<List<StartUp>> getAllStartups() {
        if (startupsLiveData.getValue() == null) {
            fetchStartups();
        }
        return startupsLiveData;}
//    public MutableLiveData<List<StartUp>> getStartupsByCategory(String category){
//        if(filteredStartupsLiveData.getValue()==null){
//            fetchFilteredStartups(category);
//        }
//        return filteredStartupsLiveData;
//    }
    public void fetchStartups() {
        apiService.getAllStartups().enqueue(new Callback<List<StartUp>>() {
            @Override
            public void onResponse(@NonNull Call<List<StartUp>> call, @NonNull Response<List<StartUp>> response) {
                Log.d(TAG,"onResponse: " + response.code());
                if (response.isSuccessful()) {
                    List<StartUp> startups = response.body();
                    startupsLiveData.postValue(startups);
                    // Populate the startUpList
                    startUpList.clear();
                    assert startups != null;
                    startUpList.addAll(startups);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<StartUp>> call, @NonNull Throwable t) {
                // Handle failure
                Log.d(TAG,"onFailure: " + t.getLocalizedMessage());
            }
        });
    }

public MutableLiveData<List<StartUp>> fetchFilteredStartups(String categoryName) {
    Log.d(TAG, "Fetching filtered startups for category: " + categoryName);

    MutableLiveData<List<StartUp>> filteredStartupsLiveData = new MutableLiveData<>();
    List<StartUp> filteredStartups = new ArrayList<>();
        apiService.getStartupsByCategory(categoryName).enqueue(new Callback<List<StartUp>>() {
            @Override
            public void onResponse(@NonNull Call<List<StartUp>> call, @NonNull Response<List<StartUp>> response) {
                Log.d(TAG, "onResponse: " + response.code());
                if (response.isSuccessful()) {
                    List<StartUp> startups = response.body();
                    if (startups != null) {
                        if ("All".equals(categoryName)) {
                            List<StartUp> startup = response.body();
                            filteredStartupsLiveData.postValue(startup);
                            // Populate the startUpList
                            filteredStartups.clear();
                            assert startups != null;
                            filteredStartups.addAll(startups);
                        } else{
                            Log.d(TAG, "Adding Startups: " + response.code());
                        for (StartUp startup : startups) {
                            if (startup.getCategory().equals(categoryName)) {
                                filteredStartups.add(startup);
                            }
                        }
                    }

                    }
                }
                filteredStartupsLiveData.setValue(filteredStartups);

            }

            @Override
            public void onFailure(@NonNull Call<List<StartUp>> call, @NonNull Throwable t) {
                // Handle failure
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });

        return filteredStartupsLiveData;

}
    public LiveData<List<StartUp>> fetchStartupsThroughSearch(String searchQuery) {
        Log.d(TAG, "Searching startups for query: " + searchQuery);
        MutableLiveData<List<StartUp>> startupsLiveData = new MutableLiveData<>();
        List<StartUp> searchStartups = new ArrayList<>();
        apiService.getStartupsThroughSearch(searchQuery).enqueue(new Callback<List<StartUp>>() {
            @Override
            public void onResponse(Call<List<StartUp>> call, Response<List<StartUp>> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.code());
                    List<StartUp> startups = response.body();
                    startupsLiveData.postValue(startups);
                    searchStartups.clear();
                    assert startups != null;
                    searchStartups.addAll(startups);
                }
                startupsLiveData.setValue(searchStartups);
            }

            @Override
            public void onFailure(Call<List<StartUp>> call, Throwable t) {
                // Handle failure
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });

        return startupsLiveData;
    }





    // Optional: Method to force a refresh from the API
    public void refreshStartups() {
        fetchStartups();
    }
}
