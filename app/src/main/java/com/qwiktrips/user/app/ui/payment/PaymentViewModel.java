package com.qwiktrips.user.app.ui.payment;

import android.content.Context;

import com.qwiktrips.user.app.ui.base.BaseViewModel;

import javax.inject.Inject;

public class PaymentViewModel extends BaseViewModel {

    @Inject
    public PaymentViewModel(Context context) {
        this.context = context;
    }
}
