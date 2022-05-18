package com.qwiktrips.user.app.ui.hairstylist.find;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.qwiktrips.user.app.R;
import com.qwiktrips.user.app.databinding.ActivityFindHairStylistBinding;
import com.qwiktrips.user.app.databinding.LayoutDrawerBinding;
import com.qwiktrips.user.app.ui.base.BindingActivity;
import com.qwiktrips.user.app.ui.hairstylist.adapter.FindHairstylistAdapter;
import com.qwiktrips.user.app.ui.payment.PaymentMethodActivity;
import com.qwiktrips.user.app.ui.profile.ProfileActivity;
import com.qwiktrips.user.app.utils.helper.OnClickHandler;
import com.qwiktrips.user.app.utils.helper.ViewItemClickHandler;

public class FindHairStylist extends BindingActivity<ActivityFindHairStylistBinding> implements ViewItemClickHandler {

    private FindHairStylistViewModel viewModel;
    private FindHairstylistAdapter adapter;
   // private LayoutDrawerBinding drawerBinding;

    @Override
    protected int getLayout() {
        return R.layout.activity_find_hair_stylist;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = getViewModel(FindHairStylistViewModel.class);
        binding.setViewHandler(this);
        binding.setViewModel(viewModel);
        init();
    }

    @Override
    protected void init() {

//        drawerBinding = LayoutDrawerBinding.bind(binding.navViewMain.getHeaderView(0));
//        drawerBinding.setViewHandler(this);
        binding.includeDrawer.setType(1);

        Glide.with(this).load(R.drawable.iimg).apply(RequestOptions.circleCropTransform()).into(binding.includeDrawer.includeLongDrawer.ivUserPic);


        adapter = new FindHairstylistAdapter(this, new OnClickHandler<View, Object, Integer>() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onClick(View view, Object o, Integer integer) {
                switch (view.getId()) {
                    case R.id.btn_learn_more: {
//                        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        startActivity(new Intent(getApplicationContext(), SearchedStylistActivity.class));
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

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onItemClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_my_qwiktrips:
            case R.id.ll_notifications:
            case R.id.ll_invite_friends: {

                break;
            }
            case R.id.ll_profile: {
                openProfile();

                break;
            }
            case R.id.ll_wallet: {
                startActivity(new Intent(getApplicationContext(), PaymentMethodActivity.class));

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
                  //  binding.navViewMain.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
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

                break;
            }

            case R.id.iv_back:{
                onBackPressed();
                break;
            }
        }
    }
}