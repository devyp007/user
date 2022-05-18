package com.qwiktrips.user.app.ui.profile.userprofile;

import android.content.Context;

import androidx.databinding.BaseObservable;

import com.qwiktrips.user.app.ui.base.BaseViewModel;

import javax.inject.Inject;

public class UserProfileViewModel extends BaseViewModel {


    private UserProfileObserver observer;

    @Inject
    public UserProfileViewModel(Context context) {
        this.context = context;
    }


    public UserProfileObserver getObserver() {
        if (observer == null) {
            observer = new UserProfileObserver();
        }
        return observer;
    }

    public static class UserProfileObserver extends BaseObservable {
        private boolean isEditClicked = false;
        private int screenObserver = 0;

        public int getScreenObserver() {
            return screenObserver;
        }

        public void setScreenObserver(int screenObserver) {
            this.screenObserver = screenObserver;
            notifyChange();
        }

        public boolean isEditClicked() {
            return isEditClicked;
        }

        public void setEditClicked(boolean editClicked) {
            isEditClicked = editClicked;
            notifyChange();
        }
    }
}
