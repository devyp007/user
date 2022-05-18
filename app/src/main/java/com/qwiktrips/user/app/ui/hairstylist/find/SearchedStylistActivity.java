package com.qwiktrips.user.app.ui.hairstylist.find;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.qwiktrips.user.app.R;
import com.qwiktrips.user.app.databinding.LayoutFeedbackDialogBinding;
import com.qwiktrips.user.app.databinding.LayoutSearchStylistProfileBinding;
import com.qwiktrips.user.app.ui.base.BindingActivity;
import com.qwiktrips.user.app.ui.booked.BookedAndFavouritesActivity;
import com.qwiktrips.user.app.ui.hairstylist.adapter.StylistServiceAdapter;
import com.qwiktrips.user.app.ui.hairstylist.select.SelectHairStyleViewModel;
import com.qwiktrips.user.app.ui.hairstylist.select.SelectHairStylistActivity;
import com.qwiktrips.user.app.ui.paymentnew.PayActivity;
import com.qwiktrips.user.app.utils.enumdata.Style;
import com.qwiktrips.user.app.utils.helper.ViewItemClickHandler;

public class SearchedStylistActivity extends BindingActivity<LayoutSearchStylistProfileBinding> implements ViewItemClickHandler {

    private SelectHairStyleViewModel viewModel;
    private StylistServiceAdapter serviceAdapter;
    private StylistServiceAdapter reviewAdapter;

    @Override
    protected int getLayout() {
        return R.layout.layout_search_stylist_profile;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = getViewModel(SelectHairStyleViewModel.class);
        init();
    }

    @Override
    protected void init() {

        binding.setViewHandler(this);

        serviceAdapter = new StylistServiceAdapter(this, Style.SERVICE, (integer, integer2, integer3) -> {
            //todo
        });
        binding.setServiceAdapter(serviceAdapter);

        reviewAdapter = new StylistServiceAdapter(this, Style.REVIEWS, (integer, integer2, integer3) -> {
            startActivity(new Intent(getApplicationContext(), BookedAndFavouritesActivity.class));
        });
        binding.rvStylistReview.setAdapter(reviewAdapter);


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
            case R.id.btn_book_appointment: {
                startActivity(new Intent(getApplicationContext(), PayActivity.class));
                break;
            }

            case R.id.iv_back: {
                onBackPressed();
                break;
            }

            case R.id.iv_star_stylist:

            case R.id.btn_message: {
                //startActivity(new Intent(getApplicationContext(), ActivityFeedback.class));
               /* binding.rlSearchedStylist.setVisibility(View.GONE);
                binding.includeLayoutAboutProvider.rlAboutProvider.setVisibility(View.VISIBLE);*/
                binding.llReview.setVisibility(View.GONE);
                binding.llPreferred.setVisibility(View.VISIBLE);
                binding.includeLayoutAboutProvider.rlAboutProvider.setVisibility(View.GONE);
                break;
            }

            case R.id.iv_question_mark:

            case R.id.tv_last_booked: {
//                binding.rlSearchedStylist.setVisibility(View.GONE);
//                binding.includeLayoutAboutProvider.rlAboutProvider.setVisibility(View.VISIBLE);
                break;
            }

            case R.id.btn_preferred_provider:

            case R.id.btn_preferred: {
                showDialog();
                break;
            }//startActivity(new Intent(getApplicationContext(), BookedAndFavouritesActivity.class));
/* binding.llReview.setVisibility(View.VISIBLE);
                binding.llPreferred.setVisibility(View.GONE);
                binding.includeLayoutAboutProvider.rlAboutProvider.setVisibility(View.GONE);*/
        }
    }

    private void showDialog() {
        Dialog dialog = new Dialog(this, R.style.AlertStyle);
        LayoutFeedbackDialogBinding feedbackDialogBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.layout_feedback_dialog, null, false);
        feedbackDialogBinding.setTitle("Thank You!");
        feedbackDialogBinding.setBody("John Smith is now in your \nQwikCare favourite Provider's List");
        feedbackDialogBinding.setViewHandler(view -> {
            dialog.dismiss();
            binding.rlSearchedStylist.setVisibility(View.VISIBLE);
            binding.includeLayoutAboutProvider.rlAboutProvider.setVisibility(View.GONE);
        });

        dialog.setContentView(feedbackDialogBinding.getRoot());
        dialog.setCancelable(false);
        dialog.show();
    }
}
