package com.qwiktrips.user.app.ui.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.qwiktrips.user.app.R;
import com.qwiktrips.user.app.databinding.MainFragmentBinding;
import com.qwiktrips.user.app.model.ServiceData;
import com.qwiktrips.user.app.ui.hairstylist.HairStylistActivity;
import com.qwiktrips.user.app.ui.home.HomeActivity;
import com.qwiktrips.user.app.ui.home.HomeBaseFragment;
import com.qwiktrips.user.app.ui.main.adapter.MainServiceAdapter;
import com.qwiktrips.user.app.utils.helper.OnClickHandler;
import com.qwiktrips.user.app.utils.helper.ViewItemClickHandler;

public class MainFragment extends HomeBaseFragment<MainFragmentBinding> implements ViewItemClickHandler {

    private MainViewModel viewModel;
    private MainServiceAdapter adapter;
    @Override
    protected int getLayout() {
        return R.layout.main_fragment;
    }

    public static MainFragment getInstance() {
        return new MainFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = getViewModel(MainViewModel.class);
        binding.setViewHandler(this);
        binding.setViewModel(viewModel);
        binding.setType(0);
        init();
    }


    @Override
    protected void init() {
        adapter = new MainServiceAdapter(getContext(), new OnClickHandler<View, ServiceData, Integer>() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onClick(View view, ServiceData serviceData, Integer integer) {
                switch (view.getId()) {
                    case R.id.rl_service: {
                        startActivity(new Intent(getContext(), HairStylistActivity.class));
                        break;
                    }
                }
            }
        });
        binding.setAdapter(adapter);
    }


    @Override
    public void onItemClicked(View view) {

    }
}