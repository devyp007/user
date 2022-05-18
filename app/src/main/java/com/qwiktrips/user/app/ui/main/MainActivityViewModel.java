package com.qwiktrips.user.app.ui.main;

import android.content.Context;

import androidx.databinding.BaseObservable;

import com.qwiktrips.user.app.ui.base.BaseViewModel;
import com.qwiktrips.user.app.ui.profile.userprofile.UserProfileViewModel;

import javax.inject.Inject;

public class MainActivityViewModel extends BaseViewModel {

    @Inject
    public MainActivityViewModel(Context context){
        this.context = context;
    }

    protected UserProfileViewModel.DrawerObserver drawerObserver;

}
