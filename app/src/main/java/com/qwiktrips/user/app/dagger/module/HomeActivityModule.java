package com.qwiktrips.user.app.dagger.module;

import com.qwiktrips.user.app.ui.main.MainFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class HomeActivityModule {

    @ContributesAndroidInjector()
    abstract MainFragment mainFragment();
}
