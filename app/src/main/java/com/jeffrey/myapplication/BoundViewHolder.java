package com.jeffrey.myapplication;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class BoundViewHolder<BINDING extends ViewDataBinding> extends RecyclerView.ViewHolder {

    public BINDING binding;

    public BoundViewHolder(View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
    }
}
