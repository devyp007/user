package com.qwiktrips.user.app.ui.hairstylist.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.qwiktrips.user.app.R;
import com.qwiktrips.user.app.databinding.LayoutItemFindHairstylistBinding;
import com.qwiktrips.user.app.utils.helper.OnClickHandler;
import com.qwiktrips.user.app.utils.helper.ViewItemClickHandler;

public class FindHairstylistAdapter extends RecyclerView.Adapter<FindHairstylistAdapter.FindHairstylistViewHolder> {

    private Context context;
    private OnClickHandler<View, Object, Integer> onClickHandler;

    public FindHairstylistAdapter(Context context, OnClickHandler<View, Object, Integer> onClickHandler) {
        this.context = context;
        this.onClickHandler = onClickHandler;
    }

    @NonNull
    @Override
    public FindHairstylistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutItemFindHairstylistBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.layout_item_find_hairstylist, parent, false);
        return new FindHairstylistViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FindHairstylistViewHolder holder, int position) {
        holder.bind();
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class FindHairstylistViewHolder extends RecyclerView.ViewHolder implements ViewItemClickHandler {

        private LayoutItemFindHairstylistBinding binding;

        public FindHairstylistViewHolder(@NonNull LayoutItemFindHairstylistBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @SuppressLint("UseCompatLoadingForDrawables")
        private void bind() {
            binding.setViewHandler(this);
                Glide.with(binding.getRoot()).load(context.getResources().getDrawable(R.drawable.user)).into(binding.ivHairstylist);
            if (getAdapterPosition() % 2 == 0) {
                binding.ivAvailable.setImageDrawable(context.getDrawable(R.drawable.available));
            } else {
                binding.ivAvailable.setImageDrawable(context.getDrawable(R.drawable.un_available));
            }
        }

        @SuppressLint("NonConstantResourceId")
        @Override
        public void onItemClicked(View view) {
            switch (view.getId()) {
                case R.id.btn_learn_more: {
                    onClickHandler.onClick(view, null, getAdapterPosition());
                    break;
                }
            }
        }
    }
}
