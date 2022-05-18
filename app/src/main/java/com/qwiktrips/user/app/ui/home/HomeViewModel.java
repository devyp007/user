package com.qwiktrips.user.app.ui.home;

import android.content.Context;
import android.content.res.TypedArray;

import androidx.databinding.BaseObservable;

import com.qwiktrips.user.app.R;
import com.qwiktrips.user.app.ui.base.BaseViewModel;

import javax.inject.Inject;

public class HomeViewModel extends BaseViewModel {

    private HomeObservable observable;


    @Inject
    public HomeViewModel(Context context) {
        this.context = context;
    }

    /*   ---------------------------------------  #region for getters and setters   -----------------------------------------*/

    public HomeObservable getObservable() {
        if (observable == null) {
            observable = new HomeObservable();
        }
        return observable;
    }

    public static class HomeObservable extends BaseObservable {
        private String heading;
        private boolean showBack;
        private int extraOpenFragment;

        public int getExtraOpenFragment() {
            return extraOpenFragment;
        }

        public void setExtraOpenFragment(int extraOpenFragment) {
            this.extraOpenFragment = extraOpenFragment;
            notifyChange();
        }

        private String[] homeTabs;

        private TypedArray homeTabIcons;

        private int activePosition;

        public int getActivePosition() {
            return activePosition;
        }

        public void setActivePosition(int activePosition) {
            this.activePosition = activePosition;
        }

        public String getHeading() {
            return heading;
        }

        public void setHeading(String heading) {
            this.heading = heading;
            notifyChange();
        }

        public boolean isShowBack() {
            return showBack;
        }

        public void setShowBack(boolean showBack) {
            this.showBack = showBack;
            notifyChange();
        }

    }
}
