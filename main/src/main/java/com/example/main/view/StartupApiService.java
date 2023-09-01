package com.example.main.view;

import com.example.main.view.adapter.Category;
import com.example.main.view.adapter.StartUp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface StartupApiService {

        @GET("startup/getAllstartup")
        Call<List<StartUp>> getAllStartups();

        @GET("startup/getAllstartupbycategory")
        Call<List<StartUp>> getStartupsByCategory(@Query("category") String category);

        @GET("startup/getAllstartupbySearch")
        Call<List<StartUp>>getStartupsThroughSearch(@Query("search")String search);


}
