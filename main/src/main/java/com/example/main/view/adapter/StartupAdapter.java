package com.example.main.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.main.R;
import com.example.main.view.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class StartupAdapter extends RecyclerView.Adapter<StartupViewHolder> {

    private String TAG ="StartupAdapter";
    private List<StartUp> startUpList;
    public static OnItemClickListener clickListener;
    private Context mContext;
    public void setStartups(List<StartUp> startups) {

        this.startUpList = startups;
        notifyDataSetChanged(); // Ensure the RecyclerView updates
        Log.d("DebugTag", "Adapter Updated with " + startups.size() + " startups");


    }
    public void updateList(List<StartUp> newList){
        startUpList.clear();
        startUpList.addAll(newList);
        notifyDataSetChanged();
        Log.d(TAG,"updateList:" +newList.size());
    }


    public StartupAdapter (List<StartUp> startUp, Context context){
        this.startUpList = startUp;
        mContext = context;

    }




    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    @NonNull
    @Override
    public StartupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rows_layout,parent,false);
        return new StartupViewHolder(view);
    }
    //   To populate items in the list certain positions
    @Override
    public void onBindViewHolder(@NonNull StartupViewHolder holder, int position) {
        Log.d(TAG, "StartupAdapter:onBindViewHolder for position: " + position);
        StartUp startUp = startUpList.get(position);
//           holder.bind(startUpList.get(position).getImage(),startUpList.get(position).getStartupName(),
//                   startUpList.get(position).getTagline());
        holder.setImageView(mContext,startUp.getImage());
//           holder.setDescription(startUp.getDescription());
        holder.setName(startUp.getStartupName());
        holder.setTagline(startUp.getTagline());
        holder.setCategory(startUp.getCategory());

    }

    @Override
    public int getItemCount() {
        return startUpList==null?0:startUpList.size();
    }

}
