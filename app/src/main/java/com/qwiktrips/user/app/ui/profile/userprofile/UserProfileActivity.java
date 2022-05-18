package com.qwiktrips.user.app.ui.profile.userprofile;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.renderscript.Allocation;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.qwiktrips.user.app.R;
import com.qwiktrips.user.app.databinding.ActivityUserProfileBinding;
import com.qwiktrips.user.app.databinding.LayoutDrawerBinding;
import com.qwiktrips.user.app.databinding.LayoutLoginBinding;
import com.qwiktrips.user.app.databinding.LayoutLogoutBinding;
import com.qwiktrips.user.app.ui.base.BindingActivity;
import com.qwiktrips.user.app.ui.booked.BookedAndFavouritesActivity;
import com.qwiktrips.user.app.ui.hairstylist.adapter.StylistServiceAdapter;
import com.qwiktrips.user.app.ui.main.MainActivity;
import com.qwiktrips.user.app.ui.map.MapActivity;
import com.qwiktrips.user.app.ui.payment.PaymentMethodActivity;
import com.qwiktrips.user.app.utils.BlurBuilder;
import com.qwiktrips.user.app.utils.enumdata.Style;
import com.qwiktrips.user.app.utils.handler.RecyclerClickHandler;
import com.qwiktrips.user.app.utils.helper.ViewItemClickHandler;
import com.suke.widget.SwitchButton;

public class UserProfileActivity extends BindingActivity<ActivityUserProfileBinding> implements ViewItemClickHandler {

    private UserProfileViewModel viewModel;
    //private LayoutDrawerBinding drawerBinding;
    private StylistServiceAdapter reviewAdapter;
    private int currentlyChanged = 1;
    private boolean istoggleOn,isBackAgain;

    @Override
    protected int getLayout() {
        return R.layout.activity_user_profile;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding.setViewHandler(this);
        viewModel = getViewModel(UserProfileViewModel.class);
        binding.setViewModel(viewModel);
        init();
    }

    Boolean isAlreadyOpened = false;

    @SuppressLint({"ClickableViewAccessibility", "UseCompatLoadingForDrawables"})
    @Override
    protected void init() {
        binding.includeDrawer.setType(viewModel.getDrawerObserver().getType());
        Glide.with(this).load(R.drawable.iimg).into(binding.includeDrawer.includeLongDrawer.ivUserPic);
        Glide.with(this).load(R.drawable.user).apply(RequestOptions.fitCenterTransform()).apply(RequestOptions.circleCropTransform()).into(binding.includeLayoutUserProfile.ivUser);
        reviewAdapter = new StylistServiceAdapter(this, Style.USER_REVIEW, (integer, integer2, integer3) -> {
            startActivity(new Intent(getApplicationContext(), BookedAndFavouritesActivity.class));
        });
        binding.includeLayoutUserProfile.rvStylistReview.setAdapter(reviewAdapter);

//        binding.includeLayoutUserProfile.profileSwitch.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
//                if (!isChecked) {
//                    showLogoutDialog(2);
//                }
//            }
//        });

//        binding.includeLayoutUserProfile.profileSwitch.setOnClickListener(v -> {
//            showLogoutDialog(2);
//        });
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
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
            case R.id.iv_close:
            case R.id.iv_menu:
            case R.id.ll_profile:
            case R.id.ll_my_qwiktrips: {
                openCloseDrawer(binding.drawerLayout);
                break;
            }
            case R.id.btn_book_appointment: {
                startActivity(new Intent(getApplicationContext(), MapActivity.class));
                break;
            }
            case R.id.ll_wallet: {
                startActivity(new Intent(getApplicationContext(), PaymentMethodActivity.class));
                openCloseDrawer(binding.drawerLayout);
                break;
            }
            case R.id.iv_home: {
                callHome();
                break;
            }

            case R.id.rl_user_image: {
                // viewModel.getObserver().setScreenObserver(1);
                break;
            }

            case R.id.iv_back1: {
                if (isBackAgain) {
                    viewModel.getObserver().setEditClicked(false);
                    binding.includeLayoutUserProfile.ivEdit.setVisibility(View.VISIBLE);
                    binding.includeLayoutUserProfile.ivLogout.setVisibility(View.VISIBLE);
                    binding.includeLayoutUserProfile.ivUserBackground.setVisibility(View.VISIBLE);
                    binding.includeLayoutUserProfile.rvStylistReview.setVisibility(View.VISIBLE);
                    isBackAgain = false;
                }else
                    super.onBackPressed();
                    viewModel.getObserver().setScreenObserver(0);
                break;
            }

            case R.id.iv_slider: {
                if (viewModel.getDrawerObserver().getType() == 1) {
                    viewModel.getDrawerObserver().setType(2);
                    binding.includeDrawer.setType(2);
                    //binding.navViewMain.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
                } else {
                    // binding.navViewMain.getLayoutParams().width = ViewGroup.LayoutParams.WRAP_CONTENT;
                    viewModel.getDrawerObserver().setType(1);
                    binding.includeDrawer.setType(1);
                }
                break;
            }

            case R.id.iv_slider_long: {
                //binding.navViewMain.getLayoutParams().width = ViewGroup.LayoutParams.WRAP_CONTENT;
                viewModel.getDrawerObserver().setType(1);
                binding.includeDrawer.setType(1);
                openCloseDrawer(binding.drawerLayout);
            }

            case R.id.iv_edit: {
                viewModel.getObserver().setEditClicked(true);
                binding.includeLayoutUserProfile.ivEdit.setVisibility(View.GONE);
                binding.includeLayoutUserProfile.ivLogout.setVisibility(View.GONE);
                binding.includeLayoutUserProfile.ivUserBackground.setVisibility(View.GONE);
                binding.includeLayoutUserProfile.rvStylistReview.setVisibility(View.GONE);
               // binding.includeLayoutUserProfile.profileSwitch.setVisibility(View.GONE);
                isBackAgain = true;
                break;
            }

            case R.id.btn_save_profile: {
                viewModel.getObserver().setEditClicked(false);
                binding.includeLayoutUserProfile.ivLogout.setVisibility(View.VISIBLE);
                binding.includeLayoutUserProfile.ivUserBackground.setVisibility(View.VISIBLE);
                binding.includeLayoutUserProfile.rvStylistReview.setVisibility(View.VISIBLE);
                //binding.includeLayoutUserProfile.profileSwitch.setVisibility(View.VISIBLE);
                break;
            }

            case R.id.iv_logout: {
                showLogoutDialog(1);
                break;
            }
        }
    }

    private void showLogoutDialog(int type) {
        isAlreadyOpened = true;
        Dialog dialog = new Dialog(this, R.style.AlertStyle);
        LayoutLogoutBinding logoutBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.layout_logout, null, false);
        if (type == 1) {
            logoutBinding.setHeader(getResources().getString(R.string.log_out));
            logoutBinding.setBody(getResources().getString(R.string.log_out_message));
            logoutBinding.setNegativeButtonName(getResources().getString(R.string.cancel));
            logoutBinding.setPositiveButtonName(getResources().getString(R.string.log_out));
        } else {
            logoutBinding.setHeader("Shanta Lana");
            if (istoggleOn)
                logoutBinding.setBody("Are You Sure You Want To Go \nOffline?");
            else logoutBinding.setBody("Are You Sure You Want To Go \nOnline?");
            logoutBinding.setNegativeButtonName("No");
            logoutBinding.setPositiveButtonName("Yes");
        }
        logoutBinding.setViewHandler(view -> {
            if (view.getId() == R.id.tv_positive) {
                if (type != 1) {
                    istoggleOn = !istoggleOn;
                    if (istoggleOn) {
                        Log.e("toggle", "ON");
                        //binding.includeLayoutUserProfile.profileSwitch.setToggleOn();
                    } else {
                        Log.e("toggle", "Off");
                        //binding.includeLayoutUserProfile.profileSwitch.setToggleOff();
                    }

                }
                currentlyChanged = 2;
            }

            dialog.dismiss();
            isAlreadyOpened = false;
        });
        dialog.setContentView(logoutBinding.getRoot());
        dialog.show();
    }

    @Override
    public void onBackPressed() {
        if (viewModel.getObserver().getScreenObserver() == 1) {
            viewModel.getObserver().setScreenObserver(0);
        } else {
            super.onBackPressed();
        }
    }
}