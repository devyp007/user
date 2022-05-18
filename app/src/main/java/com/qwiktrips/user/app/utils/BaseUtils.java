package com.qwiktrips.user.app.utils;

import android.content.Context;
import java.io.File;
import java.util.regex.Pattern;

public final class BaseUtils {

    public static void deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();
            deleteDir(dir);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (String child : children) {
                boolean success = deleteDir(new File(dir, child));
                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        } else if (dir != null && dir.isFile()) {
            return dir.delete();
        } else {
            return false;
        }
    }
    public static boolean isMobileNumberValid(String mobileNumber) {
        Pattern MOBILE_PATTERN = Pattern.compile("\\d{10}");
        return mobileNumber != null && (!mobileNumber.isEmpty() && MOBILE_PATTERN.matcher(mobileNumber).matches());
    }
}
