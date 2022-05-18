package com.qwiktrips.user.app.session;

import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import javax.inject.Inject;

public class UserSession {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Inject
    public UserSession(SharedPreferences sharedPreferences, SharedPreferences.Editor editor) {
        this.sharedPreferences = sharedPreferences;
        this.editor = editor;
    }

    public String getUserToken() {
        return getStringValue(SessionConstants.USER_TOKEN);
    }

    public String getStringValue(@NonNull String key) {
        return sharedPreferences.getString(key, null);
    }

    public boolean getBooleanValue(@NonNull String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    public boolean getIsUserVerified(@NonNull String key) {
        return sharedPreferences.getBoolean(key, true);
    }

    public int getIntValue(@NonNull String key) {
        return sharedPreferences.getInt(key, -1);
    }

//    public void updateUserToken(String userToken) {
//        saveCommit(SessionConstants.USER_TOKEN, userToken);
//    }

   /* public void userTokenSync(boolean isSync) {
        save(SessionConstants.FCM_TOKEN_SYNC, isSync);
    }*/

    /*public void updateFcmToken(@NonNull String fcmToken) {
        save(SessionConstants.FCM_TOKEN, fcmToken);
    }*/

   /* public String getFcmToken() {
        return sharedPreferences.getString(SessionConstants.FCM_TOKEN, "");
    }*/

    public void createSession(@NonNull String userToken, boolean verified) {
       /* save(SessionConstants.USER_TOKEN, userToken);
        save(SessionConstants.USER_LOGGED_IN, verified);*/
    }

    public void save(String key, String val) {
        editor.putString(key, val);
        editor.apply();
    }

    public void save(String key, boolean val) {
        editor.putBoolean(key, val);
        editor.apply();
    }

    public void save(String key, int val) {
        editor.putInt(key, val);
        editor.apply();
    }

    public void saveCommit(String key, String val) {
        editor.putString(key, val);
        editor.apply();
    }


    public void logout() {
        editor.clear();
        editor.commit();
    }
}
