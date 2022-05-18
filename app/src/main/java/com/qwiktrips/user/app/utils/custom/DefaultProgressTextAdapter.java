package com.qwiktrips.user.app.utils.custom;

public class DefaultProgressTextAdapter implements CustomProgressBar.ProgressTextAdapter {

    @Override
    public String formatText(double currentProgress) {
        return String.valueOf((int) currentProgress);
    }
}

