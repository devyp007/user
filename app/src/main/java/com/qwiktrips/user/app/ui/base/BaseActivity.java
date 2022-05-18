package com.qwiktrips.user.app.ui.base;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.gun0912.tedpermission.TedPermissionResult;
import com.qwiktrips.user.app.R;
import com.qwiktrips.user.app.dagger.factory.BaseViewModelFactory;
import com.qwiktrips.user.app.ui.hairstylist.select.SelectHairStylistActivity;
import com.qwiktrips.user.app.ui.map.MapActivity;
import com.qwiktrips.user.app.ui.profile.userprofile.UserProfileActivity;
import com.qwiktrips.user.app.utils.AppConstants;
import com.qwiktrips.user.app.utils.helper.DataItemCallback;
import com.tedpark.tedpermission.rx2.TedRx2Permission;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.functions.Consumer;

public abstract class BaseActivity extends DaggerAppCompatActivity implements BaseHandler {

    BaseViewModel baseViewModel;

    @Inject
    BaseViewModelFactory factory;

    protected <T extends ViewModel> T getViewModel(Class<T> viewModel) {
        return new ViewModelProvider(this, factory).get(viewModel);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        resume();
    }

    @Override
    protected void onPause() {
        pause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        destroy();
        super.onDestroy();
    }

    protected abstract void init();

    protected abstract void pause();

    protected abstract void resume();

    protected abstract void destroy();

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void openCloseDrawer(DrawerLayout drawerLayout) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            drawerLayout.openDrawer(GravityCompat.START);
        }
    }

    @Override
    public void openProfile() {
        startActivity(new Intent(getApplicationContext(), UserProfileActivity.class));
    }

    @Override
    public void callHome() {
        startActivity(new Intent(getApplicationContext(), MapActivity.class));
        finishAffinity();
    }

    @SuppressLint("CheckResult")
    @Override
    public void checkThePermission(String title, String deniedMsg, DataItemCallback<Integer, Integer> dataItemCallback, String... permissions) {
        TedRx2Permission.with(this)
                .setRationaleTitle(title)
                .setRationaleMessage(deniedMsg)
                .setPermissions(permissions)
                .setGotoSettingButton(true)
                .setGotoSettingButtonText(getResources().getString(R.string.text_open_setting))
                .request()
                .subscribe(new Consumer<TedPermissionResult>() {
                    @Override
                    public void accept(TedPermissionResult tedPermissionResult) throws Exception {
                        if (tedPermissionResult.isGranted()) {
                            //todo
                            dataItemCallback.onItemData(AppConstants.PERMISSION_ACCEPTED, -1);
                        } else {
                            dataItemCallback.onItemData(AppConstants.PERMISSION_DENIED, -1);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        showToast(throwable.getLocalizedMessage());
                        dataItemCallback.onItemData(AppConstants.PERMISSION_DENIED, -1);
                    }
                });
    }

    @Override
    public void hideSystemUI() {
        try {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected void openNext(String observer){
        startActivity(new Intent(this, SelectHairStylistActivity.class).putExtra("screen_observer",observer));
    }
}
