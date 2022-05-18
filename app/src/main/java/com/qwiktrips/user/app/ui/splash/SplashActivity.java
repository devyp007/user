package com.qwiktrips.user.app.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.qwiktrips.user.app.R;
import com.qwiktrips.user.app.databinding.ActivitySplashBinding;
import com.qwiktrips.user.app.ui.base.BindingActivity;
import com.qwiktrips.user.app.ui.login.LoginActivity;
import com.qwiktrips.user.app.ui.main.MainActivity;
import com.qwiktrips.user.app.ui.signup.SignupActivity;

public class SplashActivity extends BindingActivity<ActivitySplashBinding> {

    private SplashViewModel viewModel;

    @Override
    protected int getLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = getViewModel(SplashViewModel.class);

        init();
    }

    @Override
    protected void pause() {
        //todo
    }

    @Override
    protected void resume() {
        //todo
    }

    @Override
    protected void destroy() {

    }

    @Override
    protected void init() {
        new Handler()
                .postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        finishAffinity();
                    }
                }, 4000);
    }


}