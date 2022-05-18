package com.qwiktrips.user.app.ui.signup;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.annotations.SerializedName;
import com.qwiktrips.user.app.R;
import com.qwiktrips.user.app.apirepository.ApiRepository;
import com.qwiktrips.user.app.model.signup.SignupData;
import com.qwiktrips.user.app.session.UserSession;
import com.qwiktrips.user.app.ui.base.BaseViewModel;
import com.qwiktrips.user.app.ui.exception.MsgException;
import com.qwiktrips.user.app.utils.api.ApiConstant;
import com.qwiktrips.user.app.utils.api.ApiUtils;

import java.io.Serializable;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SignupViewModel extends BaseViewModel {

    private final UserSession userSession;
    private SignUpObservable signUpObservable;
    private MutableLiveData<Disposable> disposableLiveData;
    private MutableLiveData<String> msgLiveData;
    private MutableLiveData<Integer> apiLiveData;
    SignupRequest signupRequest=new SignupRequest();


    @Inject
    public SignupViewModel(Context context, UserSession userSession) {
        this.context = context;
        this.userSession = userSession;
    }


    public UserSession getUserSession() {
        return userSession;
    }

    public SignUpObservable getSignUpObservable() {
        if (signUpObservable == null) {
            signUpObservable = new SignUpObservable();
        }
        return signUpObservable;
    }

    public static class SignUpObservable extends BaseObservable {
        private boolean isArrowClicked = false;
        private String genderSelected = "Gender";
        private int screenObserver = 0;
        private boolean progressBar;


        public boolean isProgressBar() {
            return progressBar;
        }

        public void setProgressBar(boolean progressBar) {
            this.progressBar = progressBar;
            notifyChange();
        }

        public int getScreenObserver() {
            return screenObserver;
        }

        public void setScreenObserver(int screenObserver) {
            this.screenObserver = screenObserver;
            notifyChange();
        }

        public String getGenderSelected() {
            return genderSelected;
        }

        public void setGenderSelected(String genderSelected) {
            this.genderSelected = genderSelected;
            notifyChange();
        }

        public boolean isArrowClicked() {
            return isArrowClicked;
        }

        public void setArrowClicked(boolean arrowClicked) {
            isArrowClicked = arrowClicked;
            notifyChange();
        }
    }



    public class SignupRequest extends BaseObservable implements Serializable {

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
                throw new MsgException(context.getString(R.string.fullname), 0);
            }
            if (TextUtils.isEmpty(getGender()) || getGender().trim().isEmpty()) {
                throw new MsgException(context.getString(R.string.err_gender), 0);
            }
            if (TextUtils.isEmpty(getMobile()) || getMobile().trim().isEmpty()) {
                throw new MsgException(context.getString(R.string.err_mobile), 0);
            }
            if (TextUtils.isEmpty(getImage()) || getImage().trim().isEmpty()) {
                throw new MsgException(context.getString(R.string.err_image), 0);
            }
        }
    }


    public ApiRepository getApiRepository() {
        return getApiRepository();
    }

    public MutableLiveData<String> getMsgLiveData() {
        if (msgLiveData == null) {
            msgLiveData = new MutableLiveData<>();
        }
        return msgLiveData;
    }

    public void setMsgLiveData(MutableLiveData<String> msgLiveData) {
        this.msgLiveData = msgLiveData;
    }

    public MutableLiveData<Disposable> getDisposableLiveData() {
        if (disposableLiveData == null) {
            disposableLiveData = new MutableLiveData<>();
        }
        return disposableLiveData;
    }
    public MutableLiveData<Integer> getApiLiveData() {
        if (apiLiveData == null) {
            apiLiveData = new MutableLiveData<>();
        }
        return apiLiveData;
    }

    private void normalSignUp() {
        if (ApiUtils.checkInternet(context)) {
            getSignUpObservable().setProgressBar(true);
            SignupRequest request = new SignupRequest();
            request.setFullname(request.getFullname());
            request.setMobile(request.getMobile());
            request.setImage(request.getImage());
            request.setGender(request.getGender());
            getApiRepository()
                    .signup(
                            request,
                            new Observer<SignupData>() {
                                @Override
                                public void onSubscribe(Disposable d) {
                                    getDisposableLiveData().postValue(d);
                                }
                                @Override
                                public void onNext(@NonNull SignupData signupData) {
                                    //  getObserver().setProgressBar(false);
                                    if (signupData.getSuccess().equals(ApiConstant.SUCCESSFUL)) {
                                        Log.e("sussess","signup successfully");
                                        //successfully received data
                                    } else if (signupData.getSuccess().equals(ApiConstant.ERROR)) {
                                        Log.e("error","signup erro");
                                    }
                                }
                                @Override
                                public void onError(Throwable e) {
                                   // Timber.tag("NORMAL SIGNUP").d(e);
                                    getSignUpObservable().setProgressBar(false);
                                    getMsgLiveData().postValue(e.getLocalizedMessage());
                                }
                                @Override
                                public void onComplete() {

                                }
                            }
                    );
        } else {
            getMsgLiveData().setValue(context.getString(R.string.msg_no_internet));
        }
    }


}
