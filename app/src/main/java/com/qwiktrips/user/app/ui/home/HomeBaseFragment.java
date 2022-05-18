package com.qwiktrips.user.app.ui.home;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;

import com.qwiktrips.user.app.ui.base.BindingFragment;

public abstract class HomeBaseFragment<Bind extends ViewDataBinding> extends BindingFragment<Bind> {

    protected void updateAppbar(boolean showBack, @NonNull String heading) {
        ((HomeActivity) getBaseActivity()).updateAppBar(showBack, heading);
    }

    protected void onAppbarBackPress() {
        //todo
    }

    protected void openNewFragment(HomeBaseFragment fragment) {
        ((HomeActivity) getBaseActivity()).openFragment(fragment);
    }


}
