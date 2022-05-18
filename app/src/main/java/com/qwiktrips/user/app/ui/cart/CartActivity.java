package com.qwiktrips.user.app.ui.cart;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.qwiktrips.user.app.R;
import com.qwiktrips.user.app.databinding.LayoutMyCartBinding;
import com.qwiktrips.user.app.ui.base.BindingActivity;
import com.qwiktrips.user.app.utils.helper.ViewItemClickHandler;

public class CartActivity extends BindingActivity<LayoutMyCartBinding> implements ViewItemClickHandler {

    private CartViewModel viewModel;

    @Override
    protected int getLayout() {
        return R.layout.layout_my_cart;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = getViewModel(CartViewModel.class);
        binding.setViewHandler(this);
        init();
    }

    @Override
    protected void init() {
        binding.includeDrawer.setType(viewModel.getDrawerObserver().getType());
        Glide.with(this).load(R.drawable.iimg).into(binding.includeDrawer.includeLongDrawer.ivUserPic);

        CartAdapter cartAdapter = new CartAdapter(this);
        binding.rvCart.setAdapter(cartAdapter);
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
                //openCloseDrawer(binding.drawerLayout);
                callHome();
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
                //openCloseDrawer(binding.drawerLayout);
            }

            case R.id.cv_next: {

                break;
            }

            case R.id.iv_back:{
                super.onBackPressed();
            }
        }
    }
}
