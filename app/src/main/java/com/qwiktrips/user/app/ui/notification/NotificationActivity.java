package com.qwiktrips.user.app.ui.notification;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.qwiktrips.user.app.R;
import com.qwiktrips.user.app.databinding.ActivityNotificationBinding;
import com.qwiktrips.user.app.ui.appointment.MyAppointmentActivity;
import com.qwiktrips.user.app.ui.base.BindingActivity;
import com.qwiktrips.user.app.ui.notification.adapter.NotificationAdapter;
import com.qwiktrips.user.app.utils.handler.RecyclerClickHandler;
import com.qwiktrips.user.app.utils.helper.ViewItemClickHandler;

public class NotificationActivity extends BindingActivity<ActivityNotificationBinding> implements ViewItemClickHandler {

    private NotificationViewModel viewModel;

    @Override
    protected int getLayout() {
        return R.layout.activity_notification;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = getViewModel(NotificationViewModel.class);
        binding.setViewHandler(this);
        binding.setViewModel(viewModel);
        init();
    }

    @Override
    protected void init() {
        binding.includeDrawer.setType(viewModel.getDrawerObserver().getType());
        Glide.with(this).load(R.drawable.iimg).into(binding.includeDrawer.includeLongDrawer.ivUserPic);

        NotificationAdapter adapter = new NotificationAdapter(this, new RecyclerClickHandler<Integer, Integer, Integer>() {
            @Override
            public void onClick(Integer integer, Integer integer2, Integer integer3) {
                startActivity(new Intent(getApplicationContext(), MyAppointmentActivity.class));
            }
        });
        binding.rvNotification.setAdapter(adapter);
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
            case R.id.iv_back: {
                onBackPressed();
                break;
            }
           /* case R.id.iv_close:
            case R.id.iv_menu: {
                openCloseDrawer(binding.drawerLayout);
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

            case R.id.iv_home: {
                callHome();
                break;
            }

            case R.id.iv_slider_long: {
                //binding.navViewMain.getLayoutParams().width = ViewGroup.LayoutParams.WRAP_CONTENT;
                viewModel.getDrawerObserver().setType(1);
                binding.includeDrawer.setType(1);
                openCloseDrawer(binding.drawerLayout);
            }*/
        }
    }
}
