package com.qwiktrips.user.app.model.signup;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.qwiktrips.user.app.ui.exception.MsgException;

public class SignupDatanew {

    @SerializedName("fullname")
    public String fullname;

    @SerializedName("mobile")
    public String mobile;

    @SerializedName("image")
    public String image;

    @SerializedName("gender")
    public String gender;


    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public void isSignupDataValid() throws MsgException {
        if (TextUtils.isEmpty(getFullname()) || getFullname().trim().isEmpty()) {
            throw new MsgException("Please enter name", 0);
        }
        if (TextUtils.isEmpty(getGender()) || getGender().trim().isEmpty()) {
            throw new MsgException("Please enter your gender", 0);
        }
        if (TextUtils.isEmpty(getMobile()) || getMobile().trim().isEmpty()) {
            throw new MsgException("Please enter your mobile number", 0);
        }
        if (TextUtils.isEmpty(getImage()) || getImage().trim().isEmpty()) {
            throw new MsgException("Please upload photo", 0);
        }
    }

}
