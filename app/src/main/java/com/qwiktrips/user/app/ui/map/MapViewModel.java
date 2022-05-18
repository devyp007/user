package com.qwiktrips.user.app.ui.map;

import android.content.Context;

import com.qwiktrips.user.app.ui.base.BaseViewModel;

import javax.inject.Inject;

public class MapViewModel extends BaseViewModel {

    @Inject
    public MapViewModel(Context context) {
        this.context = context;
    }
}
