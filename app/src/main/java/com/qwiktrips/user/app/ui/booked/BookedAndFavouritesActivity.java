package com.qwiktrips.user.app.ui.booked;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.qwiktrips.user.app.R;
import com.qwiktrips.user.app.databinding.ActivityBookedAndFavouriteBinding;
import com.qwiktrips.user.app.ui.base.BindingActivity;
import com.qwiktrips.user.app.ui.hairstylist.adapter.FindHairstylistAdapter;
import com.qwiktrips.user.app.utils.helper.ViewItemClickHandler;

public class BookedAndFavouritesActivity extends BindingActivity<ActivityBookedAndFavouriteBinding> implements ViewItemClickHandler {

    @Override
    protected int getLayout() {
        return R.layout.activity_booked_and_favourite;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding.setViewHandler(this);
        init();
    }

    @Override
    protected void init() {

        FindHairstylistAdapter adapter = new FindHairstylistAdapter(this, (view, o, integer) -> {
            //todo
        });

        binding.rvLastBooked.setAdapter(adapter);
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
        }
    }
}