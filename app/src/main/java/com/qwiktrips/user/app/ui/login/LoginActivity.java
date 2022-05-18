package com.qwiktrips.user.app.ui.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.qwiktrips.user.app.R;
import com.qwiktrips.user.app.databinding.ActivityLoginBinding;
import com.qwiktrips.user.app.databinding.ActivityLoginNewBinding;
import com.qwiktrips.user.app.ui.base.BindingActivity;
import com.qwiktrips.user.app.ui.hairstylist.HairStylistActivity;
import com.qwiktrips.user.app.ui.main.MainActivity;
import com.qwiktrips.user.app.ui.otp.OtpVerificationActivity;
import com.qwiktrips.user.app.ui.payment.PaymentMethodActivity;
import com.qwiktrips.user.app.ui.signup.SignupActivity;
import com.qwiktrips.user.app.utils.helper.ViewItemClickHandler;

public class LoginActivity extends BindingActivity<ActivityLoginNewBinding> implements ViewItemClickHandler {

    private LoginViewModel viewModel;

    @Override
    protected int getLayout() {
        return R.layout.activity_login_new;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = getViewModel(LoginViewModel.class);
        //binding.setViewModel(viewModel);
        binding.setViewHandler(this);
        init();
    }

    @Override
    protected void init() {

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

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onItemClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_continue: {
                if (Validation()){
                    startActivity(new Intent(getApplicationContext(), OtpVerificationActivity.class));
                }
                break;
            }

            case R.id.tv_join_us: {
                startActivity(new Intent(getApplicationContext(), SignupActivity.class));
                break;
            }
        }
    }

    private boolean Validation(){

        if (binding.includePhone.etPhone.getText().toString().isEmpty()){
            binding.includePhone.etPhone.setError("Please enter your number");
            return false;
        }
        return true;
    }
}