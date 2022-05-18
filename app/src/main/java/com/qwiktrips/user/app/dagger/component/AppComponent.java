package com.qwiktrips.user.app.dagger.component;


import com.qwiktrips.user.app.application.QuickTripApp;
import com.qwiktrips.user.app.dagger.module.BaseModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Component(modules = {
        AndroidInjectionModule.class,
        BaseModule.class
})

@Singleton
public interface AppComponent {
    void inject(QuickTripApp quickTripApp);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(QuickTripApp app);

        AppComponent build();
    }
}
