package com.qwiktrips.user.app.ui.payment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.qwiktrips.user.app.R;
import com.qwiktrips.user.app.databinding.ActivityPayementMethodBinding;
import com.qwiktrips.user.app.databinding.LayoutDrawerBinding;
import com.qwiktrips.user.app.ui.base.BindingActivity;
import com.qwiktrips.user.app.utils.helper.ViewItemClickHandler;

public class PaymentMethodActivity extends BindingActivity<ActivityPayementMethodBinding> implements ViewItemClickHandler {

    private PaymentViewModel viewModel;
    //private  LayoutDrawerBinding drawerBinding;

    @Override
    protected int getLayout() {
        return R.layout.activity_payement_method;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = getViewModel(PaymentViewModel.class);
        binding.setViewHandler(this);
        binding.setViewModel(viewModel);
        init();
    }

    @Override
    protected void init() {
//        drawerBinding = LayoutDrawerBinding.bind(binding.navViewMain.getHeaderView(0));
//        drawerBinding.setViewHandler(this);
        binding.includeDrawer.setType(1);

        Glide.with(this).load(R.drawable.iimg).into(binding.includeDrawer.includeLongDrawer.ivUserPic);
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
            case R.id.iv_menu:
            case R.id.ll_my_qwiktrips:
            case R.id.ll_wallet: {
                openCloseDrawer(binding.drawerLayout);
                break;
            }
            case R.id.ll_profile: {
                openProfile();
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

            case R.id.ll_payment: {
                startActivity(new Intent(this, PaymentActivity.class));
                break;
            }
        }
    }
}