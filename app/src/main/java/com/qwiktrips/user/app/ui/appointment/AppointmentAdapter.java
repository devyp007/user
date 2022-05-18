package com.qwiktrips.user.app.ui.appointment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.qwiktrips.user.app.R;
import com.qwiktrips.user.app.databinding.LayoutAppointmentItemBinding;
import com.qwiktrips.user.app.model.appointment.AppointmentData;
import com.qwiktrips.user.app.utils.handler.RecyclerClickHandler;
import com.qwiktrips.user.app.utils.helper.ViewItemClickHandler;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder> {

    private Context context;
    private AppointmentData appointmentData;
    private RecyclerClickHandler<AppointmentData, Integer, Integer> clickHandler;

    public AppointmentAdapter(Context context, RecyclerClickHandler<AppointmentData, Integer, Integer> clickHandler) {
        this.context = context;
        this.clickHandler = clickHandler;
        appointmentData = new AppointmentData();
    }

    @NonNull
    @Override
    public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutAppointmentItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.layout_appointment_item, parent, false);
        return new AppointmentViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentViewHolder holder, int position) {
        holder.bind(appointmentData);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public void setData(AppointmentData appointmentData) {
        this.appointmentData = appointmentData;
        notifyDataSetChanged();
    }

    class AppointmentViewHolder extends RecyclerView.ViewHolder implements ViewItemClickHandler {

        private final LayoutAppointmentItemBinding binding;

        public AppointmentViewHolder(@NonNull LayoutAppointmentItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.setViewHandler(this);
        }

        private void bind(AppointmentData appointmentData) {
            try {

                if (appointmentData.getAppointmentType() == 1) {
                    binding.llEditAndDelete.setVisibility(View.VISIBLE);
                    binding.btnLearnMore.setVisibility(View.INVISIBLE);
                } else {
                    binding.llEditAndDelete.setVisibility(View.GONE);
                    binding.btnLearnMore.setVisibility(View.VISIBLE);
                }
            } catch (Exception e) {
                //todo
            }
        }

        @SuppressLint("NonConstantResourceId")
        @Override
        public void onItemClicked(View view) {
            switch (view.getId()) {
                case R.id.btn_learn_more: {
                    clickHandler.onClick(appointmentData, getAdapterPosition(), 1);
                    break;
                }
                case R.id.iv_delete: {
                    clickHandler.onClick(appointmentData, getAdapterPosition(), 2);
                    break;
                }
            }
        }
    }
}
