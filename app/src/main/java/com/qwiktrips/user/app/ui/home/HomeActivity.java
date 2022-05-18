package com.qwiktrips.user.app.ui.home;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;

import com.qwiktrips.user.app.R;
import com.qwiktrips.user.app.databinding.ActivityHomeBinding;
import com.qwiktrips.user.app.ui.base.BindingActivity;
import com.qwiktrips.user.app.ui.main.MainFragment;

public class HomeActivity extends BindingActivity<ActivityHomeBinding> {

    private HomeViewModel viewModel;

    @Override
    protected int getLayout() {
        return R.layout.activity_home;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = getViewModel(HomeViewModel.class);

        HomeBaseFragment fragment = MainFragment.getInstance();
        openFragment(fragment);
        init();
    }

    public void openFragment(@NonNull HomeBaseFragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.home_frame_layout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        viewModel.getObservable().setExtraOpenFragment(viewModel.getObservable().getExtraOpenFragment() + 1);
        Log.e("TAG", "open Fragment " + viewModel.getObservable().getExtraOpenFragment());

    }

    public void updateAppBar(boolean showBack, @NonNull String heading) {
        viewModel.getObservable().setHeading(heading);
        viewModel.getObservable().setShowBack(showBack);
    }

    @Override
    protected void init() {

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

    @Override
    public void onBackPressed() {
        if (viewModel.getObservable().getExtraOpenFragment() <= 1) {
            Log.e("Tag ", " OnBackPressed from HomeActivity : " + viewModel.getObservable().getExtraOpenFragment());
            super.onBackPressed();
        } else {
            Log.e("Tag ", " OnBackPressed from HomeActivity " + viewModel.getObservable().getExtraOpenFragment());
            getSupportFragmentManager().popBackStack();
            viewModel.getObservable().setExtraOpenFragment(viewModel.getObservable().getExtraOpenFragment() - 1);
        }
    }
}
