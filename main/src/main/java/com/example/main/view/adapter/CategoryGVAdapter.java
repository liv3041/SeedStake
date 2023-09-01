package com.example.main.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.main.R;
import com.example.main.view.OnItemClickListener;
import com.example.main.view.StartupViewModel;

import java.util.List;

public class CategoryGVAdapter extends RecyclerView.Adapter<CategoryViewHolder> {
    private List<Category> categoryList;
    StartUp startup;
    private OnCategoryClickListener categoryClickListener;
    private MutableLiveData<List<StartUp>> startupsLiveData;
    Context context;
    private Category selectedCategory;
    private StartupViewModel viewModel;

    public CategoryGVAdapter(List<Category> categoryList, Context context,OnCategoryClickListener categoryClickListener,StartupViewModel viewModel) {
        this.categoryList = categoryList;
        this.context = context;
        this.categoryClickListener = categoryClickListener;
        this.viewModel = viewModel;

    }
    public void setSelectedCategory(Category category) {
        selectedCategory = category;
        notifyDataSetChanged();
    }

    public void setCategoryClickListener(OnCategoryClickListener categoryClickListener) {
        this.categoryClickListener = categoryClickListener;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View categoryView = LayoutInflater.from(parent.getContext()).inflate(R.layout.gridview_items,parent,false);
        CategoryViewHolder cvh = new CategoryViewHolder(categoryView);
            return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = categoryList.get(position);
        holder.imageView.setImageResource(categoryList.get(position).getCategoryImage());
      holder.textView.setText(categoryList.get(position).getCategoryName());
        // Set the click listener for the category item

        holder.itemView.setOnClickListener(v -> {
            if (categoryClickListener != null) {
                categoryClickListener.onCategoryClick(category);
            }
            viewModel.setSelectedCategory(category.getCategoryName());
            Toast.makeText(context.getApplicationContext(), category.getCategoryName(),Toast.LENGTH_SHORT).show();
        });
        // Highlight the selected category
        if (selectedCategory != null && selectedCategory.equals(category)) {
            // Apply your desired styling for the selected category
            // For example, change the background color or add a border
            holder.itemView.setBackgroundColor(context.getResources().getColor(R.color.lime_green));
        } else {
            // Reset the styling for non-selected categories
            holder.itemView.setBackgroundColor(context.getResources().getColor(android.R.color.transparent));
        }
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

//     private static final String TAG = "CategoryGVAdapter";
//      private final String[] name;
//      private final int[] images;
//      Context context;
//
//    public CategoryGVAdapter(String[] name, int[] images, Context context) {
//        this.name = name;
//        this.images = images;
//        this.context = context;
//    }
//
//    @Override
//    public int getCount() {
//        return name.length;
//    }
//
//    @Override
//    public Object getItem(int i) {
//        return name[i];
//    }
//
//    @Override
//    public long getItemId(int i) {
//        return i;
//    }
//
//    @Override
//    public View getView(int i, View view, ViewGroup viewGroup) {
//        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View view1 = layoutInflater.inflate(R.layout.gridview_items,null);
//        ImageView img = view1.findViewById(R.id.iconimage);
//        TextView text = view1.findViewById(R.id.textdata);
//        img.setImageResource(images[i]);
//        text.setText(name[i]);
//        return view1;
//    }


}
