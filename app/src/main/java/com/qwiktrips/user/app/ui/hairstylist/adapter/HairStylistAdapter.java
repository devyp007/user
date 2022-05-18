package com.qwiktrips.user.app.ui.hairstylist.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.qwiktrips.user.app.R;
import com.qwiktrips.user.app.databinding.LayoutItemHairstylistBinding;
import com.qwiktrips.user.app.model.ServiceData;
import com.qwiktrips.user.app.utils.helper.OnClickHandler;
import com.qwiktrips.user.app.utils.helper.ViewItemClickHandler;

public class HairStylistAdapter extends RecyclerView.Adapter<HairStylistAdapter.HairStylistViewHolder> {

    private Context context;
    private OnClickHandler<View, ServiceData, Integer> clickHandler;

    public HairStylistAdapter(Context context,OnClickHandler<View, ServiceData, Integer> clickHandler) {
        this.context = context;
        this.clickHandler = clickHandler;
    }

    @NonNull
    @Override
    public HairStylistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutItemHairstylistBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.layout_item_hairstylist, parent, false);
        return new HairStylistViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull HairStylistViewHolder holder, int position) {
        holder.bind();
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    class HairStylistViewHolder extends RecyclerView.ViewHolder implements ViewItemClickHandler {

        private final LayoutItemHairstylistBinding binding;

        public HairStylistViewHolder(@NonNull LayoutItemHairstylistBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void bind() {
            binding.setViewHandler(this);
        }

        @SuppressLint("NonConstantResourceId")
        @Override
        public void onItemClicked(View view) {
            switch (view.getId()){
                case R.id.long_hair:
                case R.id.hair_cuts:{
                    clickHandler.onClick(view, null, getAdapterPosition());
                    break;
                }
            }
        }
    }
}
