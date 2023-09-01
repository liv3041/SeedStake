package com.example.main.view.adapter;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class ZeroSpacingItemDecoration extends RecyclerView.ItemDecoration {
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(0, 0, 0, 0); // Set all spacing to zero
    }
}
