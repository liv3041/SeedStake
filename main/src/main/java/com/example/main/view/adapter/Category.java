package com.example.main.view.adapter;

import com.google.gson.annotations.SerializedName;

public class Category extends StartUp {
    public int categoryImage;

    public  String categoryName;

    public Category(int categoryImage, String categoryName) {
        super();
        this.categoryImage = categoryImage;
        this.categoryName = categoryName;
    }

    public Category() {

    }

    public int getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(int categoryImage) {
        this.categoryImage = categoryImage;
    }

    public  String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
