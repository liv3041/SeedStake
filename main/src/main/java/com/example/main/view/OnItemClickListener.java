package com.example.main.view;

import android.view.View;

import com.example.main.view.adapter.StartUp;

public interface OnItemClickListener {
//    void onItemClick(StartUp startUp);

    void onClick(View view, int adapterPosition);
}
