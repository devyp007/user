package com.qwiktrips.user.app.dagger.component;

import android.app.Activity;

import com.qwiktrips.user.app.dagger.scope.ActivityScope;

import dagger.BindsInstance;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent
public interface ActivityComponent {

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        Builder activity(Activity activity);

        ActivityComponent build();
    }
}
