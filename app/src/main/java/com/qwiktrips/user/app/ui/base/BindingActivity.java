package com.qwiktrips.user.app.ui.base;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

public abstract class BindingActivity<Bind extends ViewDataBinding> extends BaseActivity {
    protected Bind binding;

    @LayoutRes
    protected abstract int getLayout();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideSystemUI();
        binding = DataBindingUtil.setContentView(this, getLayout());
        binding.setLifecycleOwner(this);
    }
}
