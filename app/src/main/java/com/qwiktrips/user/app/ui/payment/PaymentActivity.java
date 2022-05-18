package com.qwiktrips.user.app.ui.payment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.qwiktrips.user.app.R;
import com.qwiktrips.user.app.databinding.ActivityPaymentBinding;
import com.qwiktrips.user.app.databinding.LayoutBaseDialogBinding;
import com.qwiktrips.user.app.ui.base.BindingActivity;
import com.qwiktrips.user.app.utils.helper.ViewItemClickHandler;

public class PaymentActivity extends BindingActivity<ActivityPaymentBinding> implements ViewItemClickHandler {
    @Override
    protected int getLayout() {
        return R.layout.activity_payment;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    protected void init() {
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

            case R.id.btn_submit: {
                showDialog();
            }


        }
    }


    private void showDialog() {
        Dialog dialog = new Dialog(this, R.style.AlertStyle);
        LayoutBaseDialogBinding baseDialogBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.layout_base_dialog, null, false);

        baseDialogBinding.setViewHandler(view -> dialog.dismiss());

        dialog.setContentView(baseDialogBinding.getRoot());
        dialog.setCancelable(false);
        dialog.show();
    }

}
