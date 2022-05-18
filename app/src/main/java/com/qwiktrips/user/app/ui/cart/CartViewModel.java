package com.qwiktrips.user.app.ui.cart;

import android.content.Context;

import com.qwiktrips.user.app.ui.base.BaseViewModel;

import javax.inject.Inject;

public class CartViewModel extends BaseViewModel {

    @Inject
    public CartViewModel(Context context) {
        this.context = context;
    }
}
