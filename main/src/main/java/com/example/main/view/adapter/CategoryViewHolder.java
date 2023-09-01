package com.example.main.view.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.main.R;

import java.util.List;

public class CategoryViewHolder extends RecyclerView.ViewHolder{
    ImageView imageView;
    TextView textView;

    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView =itemView.findViewById(R.id.iconimage);
        textView =itemView.findViewById(R.id.textdata);
    }
}
