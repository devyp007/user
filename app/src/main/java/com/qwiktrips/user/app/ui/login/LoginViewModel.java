package com.qwiktrips.user.app.ui.login;

import android.content.Context;
import android.text.TextUtils;

import androidx.databinding.BaseObservable;

import com.qwiktrips.user.app.R;
import com.qwiktrips.user.app.ui.base.BaseViewModel;
import com.qwiktrips.user.app.ui.exception.MsgException;
import com.qwiktrips.user.app.utils.BaseUtils;

import javax.inject.Inject;

public class LoginViewModel extends BaseViewModel {

    @Inject
    public LoginViewModel(Context context) {
        this.context = context;
    }


    public class LoginRequest extends BaseObservable {
        private String mobileNumber;


        public String getMobileNumber() {
            return mobileNumber;
        }

        public void setMobileNumber(String mobileNumber) {
            this.mobileNumber = mobileNumber;
        }

        public void isLoginDataValid() throws MsgException {

            if (!BaseUtils.isMobileNumberValid(getMobileNumber())) {
                throw new MsgException(context.getString(R.string.msg_invalid_mobile_number), 1);
            }

        }

    }

}
