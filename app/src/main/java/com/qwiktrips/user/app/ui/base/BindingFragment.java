package com.qwiktrips.user.app.ui.base;

import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.qwiktrips.user.app.dagger.factory.BaseViewModelFactory;

import javax.inject.Inject;

public abstract class BindingFragment<Bind extends ViewDataBinding> extends BaseFragment<Bind> {

    @Inject
    BaseViewModelFactory factory;

    protected <T extends ViewModel> T getViewModel(Class<T> viewModel) {
        return new ViewModelProvider(this, factory).get(viewModel);
    }



}
