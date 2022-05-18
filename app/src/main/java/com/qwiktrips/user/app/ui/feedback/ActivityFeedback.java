package com.qwiktrips.user.app.ui.feedback;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.qwiktrips.user.app.R;
import com.qwiktrips.user.app.databinding.ActivityFeedbackTipBinding;
import com.qwiktrips.user.app.databinding.LayoutFeedbackDialogBinding;
import com.qwiktrips.user.app.ui.base.BindingActivity;
import com.qwiktrips.user.app.utils.helper.ViewItemClickHandler;

public class ActivityFeedback extends BindingActivity<ActivityFeedbackTipBinding> implements ViewItemClickHandler {

    private FeedbackViewModel viewModel;

    @Override
    protected int getLayout() {
        return R.layout.activity_feedback_tip;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = getViewModel(FeedbackViewModel.class);

        init();
    }

    @Override
    protected void init() {
        binding.setViewModel(viewModel);
        binding.setViewHandler(this);
    }

    @Override
    protected void pause() {

    }

    @Override
    protected void resume() {

    }

    @Override
    protected void destroy() {

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onItemClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back: {
                onBackPressed();
                break;
            }

            case R.id.rl_submit_your_feedback_tip: {
                showDialog();
                break;
            }

            case R.id.tv_add_tip: {
                viewModel.getObserver().setUiObserver(viewModel.getObserver().getUiObserver() + 1);
                break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (viewModel.getObserver().getUiObserver() == 0) {
            super.onBackPressed();
        } else {
            viewModel.getObserver().setUiObserver(viewModel.getObserver().getUiObserver() - 1);
        }
    }

    private void showDialog() {
        Dialog dialog = new Dialog(this, R.style.AlertStyle);
        LayoutFeedbackDialogBinding feedbackDialogBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.layout_feedback_dialog, null, false);
        feedbackDialogBinding.setTitle("Feedback Submitted");
        feedbackDialogBinding.setBody("Thank you so much for giving your \nfeedback on our experience");
        feedbackDialogBinding.setViewHandler(view -> dialog.dismiss());

        dialog.setContentView(feedbackDialogBinding.getRoot());
        dialog.setCancelable(false);
        dialog.show();
    }
}
