package com.qwiktrips.user.app.ui.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;

import com.qwiktrips.user.app.utils.helper.DataItemCallback;

import dagger.android.support.DaggerFragment;

public abstract class BaseFragment<Bind extends ViewDataBinding> extends DaggerFragment implements BaseHandler {
    protected Bind binding;

    @LayoutRes
    protected abstract int getLayout();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getLayout(), container, false);
        binding.setLifecycleOwner(this);

        return binding.getRoot();
    }

    @NonNull
    public BaseActivity getBaseActivity() {
        final FragmentActivity activity = getActivity();
        if (activity instanceof BaseActivity) {
            return (BaseActivity) activity;
        }
        throw new RuntimeException("BaseActivity is null");
    }

    protected abstract void init();

    @Override
    public void showToast(String msg) {
        getBaseActivity().showToast(msg);
    }

    @Override
    public void openCloseDrawer(DrawerLayout drawerLayout) {
        getBaseActivity().openCloseDrawer(drawerLayout);
    }

    @Override
    public void openProfile() {
        getBaseActivity().openProfile();
    }

    @Override
    public void callHome() {
        getBaseActivity().callHome();
    }

    @Override
    public void checkThePermission(String title, String deniedMsg, DataItemCallback<Integer, Integer> dataItemCallback, String... permissions) {
        getBaseActivity().checkThePermission(title, deniedMsg, dataItemCallback, permissions);
    }

    @Override
    public void hideSystemUI() {
        getBaseActivity().hideSystemUI();
    }
}
