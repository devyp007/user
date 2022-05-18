package com.qwiktrips.user.app.ui.hairstylist;

import android.annotation.SuppressLint;
import android.content.ComponentCallbacks2;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.GridLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.qwiktrips.user.app.R;
import com.qwiktrips.user.app.databinding.ActivityHairStylistBinding;
import com.qwiktrips.user.app.databinding.LayoutDrawerBinding;
import com.qwiktrips.user.app.model.ServiceData;
import com.qwiktrips.user.app.ui.base.BindingActivity;
import com.qwiktrips.user.app.ui.hairstylist.adapter.HairStylistAdapter;
import com.qwiktrips.user.app.ui.hairstylist.find.FindHairStylist;
import com.qwiktrips.user.app.ui.payment.PaymentMethodActivity;
import com.qwiktrips.user.app.utils.helper.OnClickHandler;
import com.qwiktrips.user.app.utils.helper.ViewItemClickHandler;

public class HairStylistActivity extends BindingActivity<ActivityHairStylistBinding> implements ViewItemClickHandler, ComponentCallbacks2 {

    private HairStylistViewModel viewModel;
    private HairStylistAdapter adapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_hair_stylist;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = getViewModel(HairStylistViewModel.class);
        binding.setViewModel(viewModel);
        binding.setViewHandler(this);
        init();
    }

    @Override
    protected void init() {
//        drawerBinding = LayoutDrawerBinding.bind(binding.navViewMain.getHeaderView(0));
//        drawerBinding.setViewHandler(this);
        binding.includeDrawer.setType(1);
        Glide.with(this).load(R.drawable.hair_style).override(1080, 600).into(binding.includeLayoutHairstylist.ivHairstylistTitle);
        Glide.with(this).load(R.drawable.iimg).into(binding.includeDrawer.includeLongDrawer.ivUserPic);
        adapter = new HairStylistAdapter(this, new OnClickHandler<View, ServiceData, Integer>() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onClick(View view, ServiceData serviceData, Integer integer) {
                switch (view.getId()) {
                    case R.id.long_hair:
                    case R.id.hair_cuts: {
                        startActivity(new Intent(getApplicationContext(), FindHairStylist.class));
                        break;
                    }
                }
            }
        });
        binding.setAdapter(adapter);
    }

    @Override
    protected void pause() {

    }

    @Override
    protected void resume() {

    }

    @Override
    protected void destroy() {
        System.gc();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onItemClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
            case R.id.iv_menu:
            case R.id.ll_my_qwiktrips: {
                openCloseDrawer(binding.drawerLayout);
                break;
            }
            case R.id.ll_profile: {
                openProfile();
                openCloseDrawer(binding.drawerLayout);
                break;
            }
            case R.id.ll_wallet: {
                startActivity(new Intent(getApplicationContext(), PaymentMethodActivity.class));
                openCloseDrawer(binding.drawerLayout);
                break;
            }
            case R.id.iv_home: {
                callHome();
                break;
            }

            case R.id.iv_slider: {
                if (viewModel.getDrawerObserver().getType() == 1) {
                    viewModel.getDrawerObserver().setType(2);
                    binding.includeDrawer.setType(2);
                    //binding.navViewMain.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
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
        }
    }
}