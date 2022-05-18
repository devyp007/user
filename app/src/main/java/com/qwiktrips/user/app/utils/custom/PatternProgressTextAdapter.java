package com.qwiktrips.user.app.utils.custom;

import androidx.annotation.NonNull;

public class PatternProgressTextAdapter implements CustomProgressBar.ProgressTextAdapter {

    private String pattern;

    public PatternProgressTextAdapter(String pattern) {
        this.pattern = pattern;
    }

    @NonNull
    @Override
    public String formatText(double currentProgress) {
        return String.format(pattern, currentProgress);
    }
}
