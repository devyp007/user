package com.qwiktrips.user.app.dagger.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.qwiktrips.user.app.R;
import com.qwiktrips.user.app.application.QuickTripApp;
import com.qwiktrips.user.app.dagger.component.ActivityComponent;
import com.qwiktrips.user.app.session.UserSession;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        includes = {
                UiModule.class
        },
        subcomponents = {
                ActivityComponent.class
        }
)
public class BaseModule {
    @Provides
    @Singleton
    public Application provideApplication(QuickTripApp quickTripApp) {
        return quickTripApp;
    }

    @Provides
    @Singleton
    public Context provideContext(@NonNull QuickTripApp quickTripApp) {
        return quickTripApp.getApplicationContext();
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreference(QuickTripApp quickTripApp) {
        return quickTripApp.getSharedPreferences(quickTripApp.getResources().getString(R.string.shared_pref_file_name), Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    SharedPreferences.Editor provideSharedPreferencesEditor(SharedPreferences sharedPreferences) {
        return sharedPreferences.edit();
    }

    @Provides
    @Singleton
    public UserSession provideUserSession(@NonNull SharedPreferences sharedPreferences, SharedPreferences.Editor editor) {
        return new UserSession(sharedPreferences, editor);
    }
}
