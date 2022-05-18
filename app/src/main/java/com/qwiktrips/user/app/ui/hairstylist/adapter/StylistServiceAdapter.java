package com.qwiktrips.user.app.ui.hairstylist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.qwiktrips.user.app.R;
import com.qwiktrips.user.app.databinding.LayoutLastBookedItemBinding;
import com.qwiktrips.user.app.databinding.LayoutLastBookedItemUserBinding;
import com.qwiktrips.user.app.databinding.LayoutStyleServiceBinding;
import com.qwiktrips.user.app.databinding.LayoutStylistReviewBinding;
import com.qwiktrips.user.app.databinding.LayoutStylistReviewUserBinding;
import com.qwiktrips.user.app.utils.enumdata.Style;
import com.qwiktrips.user.app.utils.handler.RecyclerClickHandler;

public class StylistServiceAdapter extends RecyclerView.Adapter<StylistServiceAdapter.HolderClass> {

    private Context context;
    private Style style;
    private RecyclerClickHandler<Integer, Integer, Integer> clickHandler;

    public StylistServiceAdapter(Context context, Style style, RecyclerClickHandler<Integer, Integer, Integer> clickHandler) {
        this.context = context;
        this.style = style;
        this.clickHandler = clickHandler;
    }

    @NonNull
    @Override
    public HolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (style == Style.SERVICE) {
            LayoutStyleServiceBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.layout_style_service, parent, false);
            return new StylistServiceViewHolder(binding);
        } else if (style == Style.USER_REVIEW) {
            LayoutLastBookedItemUserBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.layout_last_booked_item_user, parent, false);
            return new UserReviewViewHolder(binding);
        } else {
            LayoutLastBookedItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.layout_last_booked_item, parent, false);
            return new StylistReviewViewHolder(binding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull HolderClass holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public abstract static class HolderClass extends RecyclerView.ViewHolder {

        public HolderClass(@NonNull View itemView) {
            super(itemView);
        }

        public abstract void bind();
    }

    static class StylistServiceViewHolder extends HolderClass {

        private LayoutStyleServiceBinding binding;

        public StylistServiceViewHolder(@NonNull LayoutStyleServiceBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

        @Override
        public void bind() {

        }
    }

    class StylistReviewViewHolder extends HolderClass {

        private LayoutLastBookedItemBinding binding;

        public StylistReviewViewHolder(@NonNull LayoutLastBookedItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickHandler.onClick(1, 1, 1);
                }
            });
        }

        @Override
        public void bind() {

        }
    }

    class UserReviewViewHolder extends HolderClass {
        private LayoutLastBookedItemUserBinding binding;

        public UserReviewViewHolder(LayoutLastBookedItemUserBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickHandler.onClick(1, 1, 1);
                }
            });
        }

        @Override
        public void bind() {

        }
    }
}
