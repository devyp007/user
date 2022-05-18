package com.qwiktrips.user.app.ui.providers;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.qwiktrips.user.app.R;
import com.qwiktrips.user.app.databinding.ActivityPreferredProvidersBinding;
import com.qwiktrips.user.app.ui.base.BindingActivity;
import com.qwiktrips.user.app.utils.helper.ViewItemClickHandler;

public class PreferredProvidersActivity extends BindingActivity<ActivityPreferredProvidersBinding> implements ViewItemClickHandler {

    private PreferredProvidersAdapter adapter;
    private PreferredProvidersViewModel viewModel;

    @Override
    protected int getLayout() {
        return R.layout.activity_preferred_providers;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = getViewModel(PreferredProvidersViewModel.class);
        binding.setViewHandler(this);
        init();
    }

    @Override
    protected void init() {
        binding.includeDrawer.setType(viewModel.getDrawerObserver().getType());
        Glide.with(this).load(R.drawable.iimg).into(binding.includeDrawer.includeLongDrawer.ivUserPic);
        adapter = new PreferredProvidersAdapter(this);

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

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onItemClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
            case R.id.iv_menu: {
                onBackPressed();
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
        }
    }
}