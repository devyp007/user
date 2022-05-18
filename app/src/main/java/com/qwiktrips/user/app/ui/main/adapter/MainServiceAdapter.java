package com.qwiktrips.user.app.ui.main.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.qwiktrips.user.app.R;
import com.qwiktrips.user.app.databinding.LayoutMainServiceBinding;
import com.qwiktrips.user.app.model.ServiceData;
import com.qwiktrips.user.app.utils.helper.OnClickHandler;
import com.qwiktrips.user.app.utils.helper.ViewItemClickHandler;

public class MainServiceAdapter extends RecyclerView.Adapter<MainServiceAdapter.ServiceViewHolder> {

    private Context context;
    private OnClickHandler<View, ServiceData, Integer> clickHandler;

    public MainServiceAdapter(Context context, OnClickHandler<View, ServiceData, Integer> clickHandler) {
        this.context = context;
        this.clickHandler = clickHandler;
    }

    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutMainServiceBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.layout_main_service, parent, false);
        return new ServiceViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder holder, int position) {
        holder.bind();
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    class ServiceViewHolder extends RecyclerView.ViewHolder implements ViewItemClickHandler {

        private LayoutMainServiceBinding binding;

        public ServiceViewHolder(@NonNull LayoutMainServiceBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind() {
            binding.setViewHandler(this);
        }

        @SuppressLint("NonConstantResourceId")
        @Override
        public void onItemClicked(View view) {
            switch (view.getId()) {
                case R.id.rl_service: {
                    clickHandler.onClick(view, null, getAdapterPosition());
                    break;
                }
            }
        }
    }
}
