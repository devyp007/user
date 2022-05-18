package com.qwiktrips.user.app.ui.feedback;

import android.content.Context;

import androidx.databinding.BaseObservable;

import com.qwiktrips.user.app.ui.base.BaseViewModel;

import javax.inject.Inject;

public class FeedbackViewModel extends BaseViewModel {

    private FeedbackObserver observer;

    @Inject
    public FeedbackViewModel(Context context) {
        this.context = context;
    }

    public FeedbackObserver getObserver() {
        if (observer == null) {
            observer = new FeedbackObserver();
        }
        return observer;
    }

    public static class FeedbackObserver extends BaseObservable {
        private int uiObserver = 0;

        public int getUiObserver() {
            return uiObserver;
        }

        public void setUiObserver(int uiObserver) {
            this.uiObserver = uiObserver;
            notifyChange();
        }
    }
}
