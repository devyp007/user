package com.qwiktrips.user.app.ui.splash;

import android.content.Context;

import com.qwiktrips.user.app.session.UserSession;
import com.qwiktrips.user.app.ui.base.BaseViewModel;

import javax.inject.Inject;

public class SplashViewModel extends BaseViewModel {

    private UserSession userSession;

    @Inject
    public SplashViewModel(Context context, UserSession userSession) {
        this.context = context;
        this.userSession = userSession;
    }

}
