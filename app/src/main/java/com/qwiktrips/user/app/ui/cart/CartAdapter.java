package com.qwiktrips.user.app.ui.cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.qwiktrips.user.app.R;
import com.qwiktrips.user.app.databinding.LayoutCartItemBinding;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyCartViewHolder> {

    private Context context;

    public CartAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyCartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutCartItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.layout_cart_item, parent, false);
        return new MyCartViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyCartViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    static class MyCartViewHolder extends RecyclerView.ViewHolder {
        private LayoutCartItemBinding binding;

        public MyCartViewHolder(@NonNull LayoutCartItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
