package com.qwiktrips.user.app.ui.base;

import androidx.drawerlayout.widget.DrawerLayout;

import com.qwiktrips.user.app.utils.helper.DataItemCallback;

public interface BaseHandler {

    void showToast(String msg);

    void openCloseDrawer(DrawerLayout drawerLayout);

    void openProfile();

    void checkThePermission(String title,
                            String deniedMsg,
                            DataItemCallback<Integer, Integer> dataItemCallback,
                            String... permissions);

    void hideSystemUI();

    void callHome();
}
