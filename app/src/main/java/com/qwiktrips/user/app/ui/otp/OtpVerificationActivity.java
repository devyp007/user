package com.qwiktrips.user.app.ui.otp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.qwiktrips.user.app.R;
import com.qwiktrips.user.app.databinding.ActivityOtpVerificationBinding;
import com.qwiktrips.user.app.ui.base.BindingActivity;
import com.qwiktrips.user.app.ui.map.MapActivity;
import com.qwiktrips.user.app.ui.profile.ProfileActivity;
import com.qwiktrips.user.app.utils.helper.ViewItemClickHandler;

import static androidx.core.content.ContextCompat.startActivity;

public class OtpVerificationActivity extends BindingActivity<ActivityOtpVerificationBinding> implements ViewItemClickHandler {
    @Override
    protected int getLayout() {
        return R.layout.activity_otp_verification;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
    }

    @Override
    protected void init() {

        binding.setViewHandler(this);
        binding.includeEt1.etOtp.addTextChangedListener(watchOtp(1));
        binding.includeEt2.etOtp.addTextChangedListener(watchOtp(2));
        binding.includeEt3.etOtp.addTextChangedListener(watchOtp(3));
        binding.includeEt4.etOtp.addTextChangedListener(watchOtp(4));

        binding.includeEt1.etOtp.setOnFocusChangeListener(checkFocus(1));
        binding.includeEt2.etOtp.setOnFocusChangeListener(checkFocus(2));
        binding.includeEt3.etOtp.setOnFocusChangeListener(checkFocus(3));
        binding.includeEt4.etOtp.setOnFocusChangeListener(checkFocus(4));

    }


    @Override
    protected void pause() {

    }

    @Override
    protected void resume() {

    }

    @Override
    protected void destroy() {

    }
    private View.OnFocusChangeListener checkFocus(int type) {
        return (v, hasFocus) -> {
            if (v.getId() == R.id.et_otp)
                if (hasFocus) {
                    switch (type) {
                        case 1: {
//                          if (binding.includeEt1)
                            break;
                        }
                        case 2: {

                            break;
                        }
                        case 3: {

                            break;
                        }

                        case 4: {

                            break;
                        }
                    }
                } else {
                    Log.e("TAG", "not has focus");

                }
        };
    }

    private TextWatcher watchOtp(int type) {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                switch (type) {
                    case 1: {
                        if (after < count) {
                            // binding.includeEt1.etOtp.clearFocus();
                        }
                        break;
                    }
                    case 2: {
                        if (after < count) {
                            binding.includeEt1.etOtp.setEnabled(true);
                            binding.includeEt1.etOtp.requestFocus();
                        }
                        break;
                    }
                    case 3: {
                        if (after < count) {
                            binding.includeEt2.etOtp.setEnabled(true);
                            binding.includeEt2.etOtp.requestFocus();
                        }
                        break;
                    }

                    case 4: {
                        if (after < count) {
                            binding.includeEt3.etOtp.setEnabled(true);
                            binding.includeEt3.etOtp.requestFocus();
                        }
                        break;
                    }
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                switch (type) {
                    case 1: {
                        if (s.length() >= 1) {
                            binding.includeEt1.etOtp.clearFocus();
                            binding.includeEt2.etOtp.requestFocus();
                            binding.includeEt1.etOtp.setEnabled(false);
                        }

                        break;
                    }
                    case 2: {
                        if (s.length() >= 1) {
                            binding.includeEt2.etOtp.clearFocus();
                            binding.includeEt3.etOtp.requestFocus();
                            binding.includeEt2.etOtp.setEnabled(false);
                        }
                        break;
                    }
                    case 3: {
                        if (s.length() >= 1) {
                            binding.includeEt3.etOtp.clearFocus();
                            binding.includeEt4.etOtp.requestFocus();
                            binding.includeEt3.etOtp.setEnabled(false);
                        }
                        break;
                    }

                    case 4: {
                        if (s.length() >= 1) {
                            //binding.includeEt4.etOtp.clearFocus();
                            //binding.includeEt4.etOtp.setEnabled(false);
                        }
                        break;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onItemClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_signup:
            case R.id.iv_next:
            case R.id.btn_signup: {
                startActivity(new Intent(getApplicationContext(), MapActivity.class));
            }
        }
    }
}
