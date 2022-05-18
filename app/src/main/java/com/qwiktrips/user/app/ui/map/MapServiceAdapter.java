package com.qwiktrips.user.app.ui.map;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.qwiktrips.user.app.R;
import com.qwiktrips.user.app.databinding.LayoutMapItemBinding;
import com.qwiktrips.user.app.utils.handler.RecyclerClickHandler;
import com.qwiktrips.user.app.utils.helper.ViewItemClickHandler;

public class MapServiceAdapter extends RecyclerView.Adapter<MapServiceAdapter.MapViewHolder> {

    RecyclerClickHandler<Object, Integer, Integer> clickHandler;

    public MapServiceAdapter(RecyclerClickHandler<Object, Integer, Integer> clickHandler) {
        this.clickHandler = clickHandler;
    }

    @NonNull
    @Override
    public MapViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutMapItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.layout_map_item, parent, false);
        return new MapViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MapViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class MapViewHolder extends RecyclerView.ViewHolder implements ViewItemClickHandler {

        private LayoutMapItemBinding binding;

        public MapViewHolder(@NonNull LayoutMapItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.setViewHandler(this);
        }

        @Override
        public void onItemClicked(View view) {
            if (view.getId() == R.id.iv_add) {
                clickHandler.onClick(null, getAdapterPosition(), 1);
            }
        }
    }
}