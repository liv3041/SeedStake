package com.example.main.view.adapter;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.main.R;

import java.net.CookieHandler;
import java.util.List;

public class StartupViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private ImageView imageView;
    private TextView name , tagline , category, description;
    private CardView card;

    public StartupViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.item_startUp_image);
        name = itemView.findViewById(R.id.item_startUP_name);
        tagline = itemView.findViewById(R.id.item_tagline);
        card = itemView.findViewById(R.id.cardView);
        category =itemView.findViewById(R.id.item_category);
//        description = itemView.findViewById(R.id.tv_description);4
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StartupAdapter.clickListener != null) {
                    StartupAdapter.clickListener.onClick(v, getAdapterPosition());
                }
            }
        });
    }
//    public void bind(Integer image, String startUp,String tag){
//        imageView.setImageResource(image);
//        name.setText(startUp);
//        tagline.setText(tag);
//        itemView.setTag(itemView);
//        itemView.setOnClickListener(this);
//    }
  public void setCategory(String category){this.category.setText(category);}
    public void setName(String name) {
        this.name.setText(name);
    }

    public void setTagline(String tagline) {
        this.tagline.setText(tagline);
    }

    public void setDescription(String description) {
        this.description.setText(description);
    }

    public void setCard(CardView card) {
        this.card = card;
    }

    public void setImageView(Context context , String url) {
        Glide.with(context)
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.busines)
                .apply(new RequestOptions().override(100,100))
                .into(this.imageView);
    }


    @Override
    public void onClick(View view) {
        if (StartupAdapter.clickListener != null) StartupAdapter.clickListener.onClick(view, getAdapterPosition());
    }
}




