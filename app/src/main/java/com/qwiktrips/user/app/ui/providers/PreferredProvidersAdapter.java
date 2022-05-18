package com.qwiktrips.user.app.ui.providers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.qwiktrips.user.app.R;
import com.qwiktrips.user.app.databinding.LayoutPreferredProvidersItemBinding;

public class PreferredProvidersAdapter extends RecyclerView.Adapter<PreferredProvidersAdapter.PreferredProvidersViewHandler> {

    private Context context;

    public PreferredProvidersAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public PreferredProvidersViewHandler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutPreferredProvidersItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.layout_preferred_providers_item, parent, false);
        return new PreferredProvidersViewHandler(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PreferredProvidersViewHandler holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 8;
    }

    static class PreferredProvidersViewHandler extends RecyclerView.ViewHolder {
        private LayoutPreferredProvidersItemBinding binding;

        public PreferredProvidersViewHandler(@NonNull LayoutPreferredProvidersItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
