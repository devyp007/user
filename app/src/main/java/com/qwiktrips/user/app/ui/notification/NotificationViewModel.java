package com.qwiktrips.user.app.ui.notification;

import android.content.Context;

import com.qwiktrips.user.app.ui.base.BaseViewModel;

import javax.inject.Inject;

public class NotificationViewModel extends BaseViewModel {

    @Inject
    public NotificationViewModel(Context context) {
        this.context = context;
    }
}
