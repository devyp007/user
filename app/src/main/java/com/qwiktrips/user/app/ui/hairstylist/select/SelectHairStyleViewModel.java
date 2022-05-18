package com.qwiktrips.user.app.ui.hairstylist.select;

import android.content.Context;

import androidx.databinding.BaseObservable;

import com.qwiktrips.user.app.ui.base.BaseViewModel;

import javax.inject.Inject;

public class SelectHairStyleViewModel extends BaseViewModel {

    private SelectStyleObserver observer;

    @Inject
    public SelectHairStyleViewModel(Context context) {
        this.context = context;
    }

    public SelectStyleObserver getObserver() {
        if (observer == null) {
            observer = new SelectStyleObserver();
        }
        return observer;
    }

    public static class SelectStyleObserver extends BaseObservable {
        private boolean isGpsChecked;
        private boolean isDropDown = false;
        private String styleSelectText = "Select Your Hairstyle";

        public String getStyleSelectText() {
            return styleSelectText;
        }

        public void setStyleSelectText(String styleSelectText) {
            this.styleSelectText = styleSelectText;
            notifyChange();
        }

        public boolean isDropDown() {
            return isDropDown;
        }

        public void setDropDown(boolean dropDown) {
            isDropDown = dropDown;
            notifyChange();
        }

        public boolean isGpsChecked() {
            return isGpsChecked;
        }

        public void setGpsChecked(boolean gpsChecked) {
            isGpsChecked = gpsChecked;
            notifyChange();
        }

        /**
         * 0 = default
         * 1 = payment page
         * 2 = confirmation page
         * 3 = appointment confirmed screen
         *
         */

        private int screenHandler;

        public int getScreenHandler() {
            return screenHandler;
        }

        public void setScreenHandler(int screenHandler) {
            this.screenHandler = screenHandler;
            notifyChange();
        }
    }
}
