package com.qwiktrips.user.app.ui.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.qwiktrips.user.app.R;
import com.qwiktrips.user.app.databinding.ActivityMainBinding;
import com.qwiktrips.user.app.model.ServiceData;
import com.qwiktrips.user.app.ui.appointment.MyAppointmentActivity;
import com.qwiktrips.user.app.ui.base.BindingActivity;
import com.qwiktrips.user.app.ui.cart.CartActivity;
import com.qwiktrips.user.app.ui.hairstylist.HairStylistActivity;
import com.qwiktrips.user.app.ui.hairstylist.select.SelectHairStylistActivity;
import com.qwiktrips.user.app.ui.main.adapter.MainServiceAdapter;
import com.qwiktrips.user.app.ui.notification.NotificationActivity;
import com.qwiktrips.user.app.ui.providers.PreferredProvidersActivity;
import com.qwiktrips.user.app.utils.helper.OnClickHandler;
import com.qwiktrips.user.app.utils.helper.ViewItemClickHandler;

public class MainActivity extends BindingActivity<ActivityMainBinding> implements ViewItemClickHandler {

    private MainActivityViewModel viewModel;
    private MainServiceAdapter adapter;
//    private LayoutDrawerBinding drawerBinding;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = getViewModel(MainActivityViewModel.class);
        binding.setViewHandler(this);
        binding.setViewModel(viewModel);
        binding.setType(0);
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void init() {

//        drawerBinding = LayoutDrawerBinding.bind(binding.navViewMain.getHeaderView(0));
//        drawerBinding.setViewHandler(this);
        binding.includeDrawer.setType(1);
        binding.includeDrawer.setType(viewModel.getDrawerObserver().getType());
        Glide.with(this).load(R.drawable.iimg).into(binding.includeDrawer.includeLongDrawer.ivUserPic);

        adapter = new MainServiceAdapter(this, new OnClickHandler<View, ServiceData, Integer>() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onClick(View view, ServiceData serviceData, Integer integer) {
                switch (view.getId()) {
                    case R.id.rl_service: {
                        startActivity(new Intent(getApplicationContext(), HairStylistActivity.class));
                        break;
                    }
                }
            }
        });
        binding.setAdapter(adapter);
    }

    @Override
    protected void pause() {
        //todo
    }

    @Override
    protected void resume() {
        //todo
    }

    @Override
    protected void destroy() {
        // System.gc();
        //BaseUtils.deleteCache(this);
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onItemClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
            case R.id.iv_menu:
            case R.id.ll_help: {
                openCloseDrawer(binding.drawerLayout);
                break;
            }
            case R.id.ll_invite_friends: {
                startActivity(new Intent(getApplicationContext(), PreferredProvidersActivity.class));
                break;
            }
            case R.id.ll_profile: {
                openProfile();
                openCloseDrawer(binding.drawerLayout);
                break;
            }
            case R.id.ll_wallet: {
                startActivity(new Intent(getApplicationContext(), SelectHairStylistActivity.class));
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

            case R.id.iv_slider_long: {
                //binding.navViewMain.getLayoutParams().width = ViewGroup.LayoutParams.WRAP_CONTENT;
                viewModel.getDrawerObserver().setType(1);
                binding.includeDrawer.setType(1);
                openCloseDrawer(binding.drawerLayout);
            }

            case R.id.ll_notifications: {
                openCloseDrawer(binding.drawerLayout);
                startActivity(new Intent(getApplicationContext(), NotificationActivity.class));
                break;
            }

            case R.id.ll_my_qwiktrips: {
                openCloseDrawer(binding.drawerLayout);
                startActivity(new Intent(getApplicationContext(), MyAppointmentActivity.class));
                break;
            }

            case R.id.ll_cart: {
                openCloseDrawer(binding.drawerLayout);
                startActivity(new Intent(getApplicationContext(), CartActivity.class));
                break;
            }

        }
    }

    @Override
    public void onBackPressed() {
        if (binding.getType() != 0) {
            binding.setType(0);
        } else {
            super.onBackPressed();
        }
    }
}