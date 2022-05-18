package com.qwiktrips.user.app.ui.providers;

import android.content.Context;

import com.qwiktrips.user.app.ui.base.BaseViewModel;

import javax.inject.Inject;

public class PreferredProvidersViewModel extends BaseViewModel {

    @Inject
    public PreferredProvidersViewModel(Context context) {
        this.context = context;
    }
}
