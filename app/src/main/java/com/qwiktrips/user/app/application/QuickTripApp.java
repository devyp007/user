package com.qwiktrips.user.app.application;

import android.app.Application;

import com.qwiktrips.user.app.appenvironment.AppEnvironment;
import com.qwiktrips.user.app.dagger.component.AppComponent;
import com.qwiktrips.user.app.dagger.component.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;

public class QuickTripApp extends Application implements HasAndroidInjector {

    static QuickTripApp quickTripApp;
    static AppComponent appComponent;
    static AppEnvironment appEnvironment;


    @Inject
    DispatchingAndroidInjector<Object> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();

        quickTripApp = this;
        appComponent = DaggerAppComponent.builder().application(this).build();
        appComponent.inject(this);
    }

    public static QuickTripApp getQuickTripApp() {
        return quickTripApp;
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public AndroidInjector<Object> androidInjector() {
        return dispatchingAndroidInjector;
    }
    public AppEnvironment getAppEnvironment() {
        return appEnvironment;
    }
}
