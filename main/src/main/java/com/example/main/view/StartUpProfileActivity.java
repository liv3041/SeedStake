package com.example.main.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.main.R;
import com.example.main.view.adapter.StartUp;

import java.util.ArrayList;
import java.util.List;

public class StartUpProfileActivity extends AppCompatActivity {
    ImageView imageView;
    TextView startupName, description;
    RecyclerView recyclerView;
    Button btn_invest , btn_bookmark;
    LinearLayoutManager linearLayoutManager;
//    StartUp startUp;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_statrt_up_profile);
        imageView=findViewById(R.id.item_startUp_image);
        startupName = findViewById(R.id.item_startUP_name);
        description=findViewById(R.id.tv_description);
        btn_invest = findViewById(R.id.btn_invest);
        btn_bookmark = findViewById(R.id.btn_bookmark);

        Intent intent = getIntent();
        StartUp startUp;
        if (intent != null) {
            startUp = intent.getParcelableExtra("startup");
            if (startUp != null) {
                Log.d("DebugTag", "StartUp object retrieved: " + startUp.getStartupName());
                // Now you can use the startUp object to populate your UI

                Log.d("DebugTag", "Startup Image URL: " + startUp.getImage());
                Log.d("DebugTag", "Startup Name: " + startUp.getStartupName());
                Log.d("DebugTag", "Startup Description: " + startUp.getDescription());
                setImageView(context,startUp.getImage());
                startupName.setText(startUp.getStartupName());
                description.setText(startUp.getDescription());
            }else {
                Log.e("DebugTag", "StartUp object is null");
            }
        }else {
            Log.e("DebugTag", "Intent is null");
        }
    }

    private void setDescription(String description) {
        this.description.setText(description);
    }

    private void setName(String startupName) {
        this.startupName.setText(startupName);
    }

    private void setImageView(Context context, String url) {
        Glide.with(this)
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.busines)
                .apply(new RequestOptions().override(100,100))
                .into(this.imageView);
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}