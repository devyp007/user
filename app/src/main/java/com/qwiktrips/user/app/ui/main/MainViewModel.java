package com.qwiktrips.user.app.ui.main;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.qwiktrips.user.app.ui.base.BaseViewModel;

import javax.inject.Inject;

public class MainViewModel extends BaseViewModel {

    @Inject
    public MainViewModel(Context context) {
        this.context = context;
    }
}