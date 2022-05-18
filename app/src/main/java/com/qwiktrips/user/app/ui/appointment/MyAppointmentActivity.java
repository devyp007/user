package com.qwiktrips.user.app.ui.appointment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.qwiktrips.user.app.R;
import com.qwiktrips.user.app.databinding.ActivityMyAppointmentsBinding;
import com.qwiktrips.user.app.databinding.LayoutDeleteDialogBinding;
import com.qwiktrips.user.app.databinding.LayoutLogoutBinding;
import com.qwiktrips.user.app.model.appointment.AppointmentData;
import com.qwiktrips.user.app.ui.base.BindingActivity;
import com.qwiktrips.user.app.ui.feedback.ActivityFeedback;
import com.qwiktrips.user.app.ui.home.HomeActivity;
import com.qwiktrips.user.app.utils.handler.RecyclerClickHandler;
import com.qwiktrips.user.app.utils.helper.ViewItemClickHandler;

public class MyAppointmentActivity extends BindingActivity<ActivityMyAppointmentsBinding> implements ViewItemClickHandler {

    private MyAppointmentViewModel viewModel;
    private AppointmentAdapter appointmentAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_my_appointments;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = getViewModel(MyAppointmentViewModel.class);
        binding.setViewHandler(this);
        binding.setViewModel(viewModel);

        init();
    }

    @Override
    protected void init() {
        viewModel.getObserver().setAppointmentStatus(1);
        binding.includeDrawer.setType(1);
        Glide.with(this).load(R.drawable.iimg).into(binding.includeDrawer.includeLongDrawer.ivUserPic);

        appointmentAdapter = new AppointmentAdapter(this, (appointmentData, position, value) -> {
            switch (value) {
                case 1: {
                    //viewModel.getObserver().setScreenHandler(1);
                    break;
                }
                case 2: {
                    showDeleteDialog();
                    break;
                }
            }
        });
        appointmentAdapter.setData(viewModel.getObserver().getAppointmentData());
        binding.includeLayoutAppointment.rvAppointment.setAdapter(appointmentAdapter);
    }

    @Override
    protected void pause() {

    }

    @Override
    protected void resume() {

    }

    @Override
    protected void destroy() {

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onItemClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
            case R.id.iv_menu: {
                openCloseDrawer(binding.drawerLayout);
                break;
            }
            case R.id.iv_home: {
                callHome();
                break;
            }
            case R.id.tv_active: {
                viewModel.getObserver().setAppointmentStatus(1);
                viewModel.getObserver().getAppointmentData().setAppointmentType(1);
                appointmentAdapter.setData(viewModel.getObserver().getAppointmentData());
                break;
            }

            case R.id.tv_completed: {
                viewModel.getObserver().setAppointmentStatus(2);
                viewModel.getObserver().getAppointmentData().setAppointmentType(2);
                appointmentAdapter.setData(viewModel.getObserver().getAppointmentData());
                break;
            }

            case R.id.tv_cancelled: {
                viewModel.getObserver().setAppointmentStatus(3);
                viewModel.getObserver().getAppointmentData().setAppointmentType(3);
                appointmentAdapter.setData(viewModel.getObserver().getAppointmentData());
                break;
            }

            case R.id.iv_slider: {
                if (viewModel.getDrawerObserver().getType() == 1) {
                    viewModel.getDrawerObserver().setType(2);
                    binding.includeDrawer.setType(2);
                    // binding.navViewMain.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
                } else {
                    //binding.navViewMain.getLayoutParams().width = ViewGroup.LayoutParams.WRAP_CONTENT;
                    viewModel.getDrawerObserver().setType(1);
                    binding.includeDrawer.setType(1);
                }
                break;
            }

            case R.id.iv_slider_long: {
                //binding.navViewMain.getLayoutParams().width = ViewGroup.LayoutParams.WRAP_CONTENT;
                viewModel.getDrawerObserver().setType(1);
                binding.includeDrawer.setType(1);
            }

            case R.id.rl_share_your_feedback: {
                //viewModel.getObserver().setScreenHandler(2);
                startActivity(new Intent(this, ActivityFeedback.class));
                break;
            }

            case R.id.rl_get_your_receipt: {
                viewModel.getObserver().setScreenHandler(3);
                break;
            }


            case R.id.rl_thank_you: {
                viewModel.getObserver().setScreenHandler(4);
                break;
            }

            case R.id.iv_back: {
                onBackPressed();
                break;
            }

            case R.id.rl_view_all_appointment: {
                if (viewModel.getObserver().getAppointmentStatus() != 1) {
                    viewModel.getObserver().setScreenHandler(1);
                }
                break;
            }
        }
    }

    private void showDeleteDialog() {
        Dialog dialog = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar);/*android.R.style.Theme_Translucent_NoTitleBar*/
        LayoutDeleteDialogBinding deleteDialogBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.layout_delete_dialog, null, false);
        deleteDialogBinding.setViewHandler(view -> {
            dialog.dismiss();
        });
        dialog.setCancelable(false);
        dialog.setContentView(deleteDialogBinding.getRoot());
        dialog.show();
    }

    @Override
    public void onBackPressed() {
        if (viewModel.getObserver().getScreenHandler() == 0) {
            super.onBackPressed();
        } else
            viewModel.getObserver().setScreenHandler(viewModel.getObserver().getScreenHandler() - 1);
    }
}
