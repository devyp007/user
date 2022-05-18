package com.qwiktrips.user.app.ui.hairstylist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.qwiktrips.user.app.R;
import com.qwiktrips.user.app.databinding.LayoutStylistReviewBinding;

public class StylistReviewAdapter extends RecyclerView.Adapter<StylistReviewAdapter.StylistReviewViewHolder> {

    private Context context;

    public StylistReviewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public StylistReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutStylistReviewBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.layout_stylist_review, parent, false);
        return new StylistReviewViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull StylistReviewViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class StylistReviewViewHolder extends RecyclerView.ViewHolder{

        private LayoutStylistReviewBinding binding;

        public StylistReviewViewHolder(@NonNull LayoutStylistReviewBinding binding) {
            super(binding.getRoot());
        }
    }
}
