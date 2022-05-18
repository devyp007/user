package com.qwiktrips.user.app.ui.hairstylist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.qwiktrips.user.app.R;
import com.qwiktrips.user.app.databinding.LayoutStyleItemBinding;

public class SelectStyleAdapter extends RecyclerView.Adapter<SelectStyleAdapter.SelectStyleViewHolder> {

    private Context context;

    public SelectStyleAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public SelectStyleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutStyleItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.layout_style_item, parent, false);
        return new SelectStyleViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectStyleViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    static class SelectStyleViewHolder extends RecyclerView.ViewHolder {

        private LayoutStyleItemBinding binding;

        public SelectStyleViewHolder(@NonNull LayoutStyleItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
