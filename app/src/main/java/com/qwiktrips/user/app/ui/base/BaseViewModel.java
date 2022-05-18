package com.qwiktrips.user.app.ui.base;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.databinding.BaseObservable;
import androidx.lifecycle.ViewModel;

@SuppressLint("StaticFieldLeak")
public class BaseViewModel extends ViewModel {

    protected Context context;

    protected DrawerObserver drawerObserver;

    public DrawerObserver getDrawerObserver() {
        if (drawerObserver == null) {
            drawerObserver = new DrawerObserver();
        }
        return drawerObserver;
    }

    public static class DrawerObserver extends BaseObservable {
        private int type = 1;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
            notifyChange();
        }
    }





}
