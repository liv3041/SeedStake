package com.example.main.view.adapter;

import android.os.Parcelable;
import android.widget.ImageView;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class StartUp implements Serializable {
    @SerializedName("name")
    private String startupName;
    @SerializedName("tagline")
    String tagline;
    @SerializedName("description")
    String description;
    @SerializedName("image")
    private String image;
    @SerializedName("category")
    private String category;

    public StartUp() {

    }

    public String getCategory() {
        return category;
    }

//    public void setCategory(String category) {
//        this.category = category;
//    }

    public StartUp(String startupName, String tagline, String image, String description, String category) {
        this.startupName = startupName;
        this.tagline = tagline;
        this.image = image;
        this.description = description;
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartupName() {
        return startupName;
    }

//    public void setStartupName(String startupName) {
//        this.startupName = startupName;
//    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
