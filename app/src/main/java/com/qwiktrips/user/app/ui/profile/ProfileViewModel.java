package com.qwiktrips.user.app.ui.profile;

import android.content.Context;

import com.qwiktrips.user.app.ui.base.BaseViewModel;

import javax.inject.Inject;

public class ProfileViewModel extends BaseViewModel {

    @Inject
    public ProfileViewModel(Context context) {
        this.context = context;
    }
}
