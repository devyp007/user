package com.qwiktrips.user.app.ui.profile;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.qwiktrips.user.app.R;
import com.qwiktrips.user.app.databinding.ActivityProfileBinding;
import com.qwiktrips.user.app.databinding.LayoutDrawerBinding;
import com.qwiktrips.user.app.ui.base.BindingActivity;
import com.qwiktrips.user.app.ui.map.MapActivity;
import com.qwiktrips.user.app.ui.payment.PaymentMethodActivity;
import com.qwiktrips.user.app.ui.profile.userprofile.UserProfileActivity;
import com.qwiktrips.user.app.utils.helper.ViewItemClickHandler;

public class ProfileActivity extends BindingActivity<ActivityProfileBinding> implements ViewItemClickHandler {

    private ProfileViewModel viewModel;
    //private LayoutDrawerBinding drawerBinding;

    @Override
    protected int getLayout() {
        return R.layout.activity_profile;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = getViewModel(ProfileViewModel.class);
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
        Glide.with(this).load(R.drawable.user).apply(RequestOptions.circleCropTransform()).into(binding.includeLayoutProfile.ivUser);
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
            case R.id.ll_my_qwiktrips: {
                openCloseDrawer(binding.drawerLayout);
                break;
            }
            case R.id.btn_book_appointment: {
                startActivity(new Intent(getApplicationContext(), MapActivity.class));
                //openCloseDrawer(binding.drawerLayout);
                break;
            }
            case R.id.ll_wallet: {
                startActivity(new Intent(getApplicationContext(), PaymentMethodActivity.class));
                openCloseDrawer(binding.drawerLayout);
                break;
            }
            case R.id.ll_profile: {
                startActivity(new Intent(getApplicationContext(), UserProfileActivity.class));
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

//            case R.id.iv_back: {
//                super.onBackPressed();
//            }
        }
    }
}