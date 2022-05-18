package com.qwiktrips.user.app.ui.notification.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.qwiktrips.user.app.R;
import com.qwiktrips.user.app.databinding.LayoutNotificationItemBinding;
import com.qwiktrips.user.app.utils.handler.RecyclerClickHandler;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {

    private Context context;
    private RecyclerClickHandler<Integer, Integer, Integer> clickHandler;

    public NotificationAdapter(Context context, RecyclerClickHandler<Integer, Integer, Integer> clickHandler) {
        this.context = context;
        this.clickHandler = clickHandler;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutNotificationItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.layout_notification_item, parent, false);
        return new NotificationViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class NotificationViewHolder extends RecyclerView.ViewHolder {

        private LayoutNotificationItemBinding binding;

        public NotificationViewHolder(@NonNull LayoutNotificationItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickHandler.onClick(0, 0, 0);
                }
            });
        }
    }
}
