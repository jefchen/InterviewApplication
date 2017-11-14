package com.jeffrey.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jeffrey.myapplication.databinding.CountCellBinding;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CountAdapter extends RecyclerView.Adapter<BoundViewHolder<CountCellBinding>> {

    List<Industry> industryList = new ArrayList<>();

    public void loadIndustries(@NonNull Context context) {
        InputStream inputStream = context.getResources().openRawResource(R.raw.industry);
        Gson gson = new Gson();
        Type collectionType = new TypeToken<Collection<Industry>>() {}.getType();
        try {
            InputStreamReader isr = new InputStreamReader(inputStream, "utf-8");
            industryList = gson.fromJson(isr, collectionType);
        } catch (Throwable t) {
            // do nothing
        } finally {
            try {
                inputStream.close();
            } catch (Throwable e) {

            }
        }
    }

    @Override
    public BoundViewHolder<CountCellBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.count_cell, parent, false);
        return new BoundViewHolder<>(view);
    }

    @Override
    public void onBindViewHolder(BoundViewHolder<CountCellBinding> holder, int position) {
        holder.binding.setCountText("My count is: " + position);
        holder.binding.setIndustry(industryList.get(position));

        Glide.with(holder.itemView.getContext())
             .load(industryList.get(position).url)
             .centerCrop()
             .placeholder(R.drawable.ic_launcher_background)
             .dontAnimate()
             .into(holder.binding.imageView);

        holder.binding.getRoot().setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), DetailActivity.class);
            view.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return industryList.size();
    }
}
