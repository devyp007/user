package com.qwiktrips.user.app.ui.hairstylist.select;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.qwiktrips.user.app.R;
import com.qwiktrips.user.app.databinding.ActivitySelectHairStylistBinding;
import com.qwiktrips.user.app.ui.appointment.MyAppointmentActivity;
import com.qwiktrips.user.app.ui.base.BindingActivity;
import com.qwiktrips.user.app.ui.hairstylist.adapter.SelectStyleAdapter;
import com.qwiktrips.user.app.ui.hairstylist.find.FindHairStylist;
import com.qwiktrips.user.app.ui.home.HomeActivity;
import com.qwiktrips.user.app.ui.map.MapActivity;
import com.qwiktrips.user.app.utils.helper.ViewItemClickHandler;
import com.suke.widget.SwitchButton;

public class SelectHairStylistActivity extends BindingActivity<ActivitySelectHairStylistBinding> implements ViewItemClickHandler {

    private SelectHairStyleViewModel viewModel;
    private SelectStyleAdapter selectStyleAdapter;
    String screenType ;
    String[] filterItem={"Short Hair","Long Hair","Coloring","Weave Extensions","Blowouts"};
    boolean isSpinnerSet = false;


    @Override
    protected int getLayout() {
        return R.layout.activity_select_hair_stylist;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = getViewModel(SelectHairStyleViewModel.class);
        binding.setViewHandler(this);
        binding.setViewModel(viewModel);
        screenType=  getIntent().getExtras().getString("screen_observer");

        hide();

        init();
        ArrayAdapter aa = new ArrayAdapter(this,R.layout.simple_spinner_item,filterItem);
        aa.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        binding.spinner.setAdapter(aa);

        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(isSpinnerSet){
                    binding.tvStyleSelect.setText(aa.getItem(binding.spinner.getSelectedItemPosition()).toString());
                }
                isSpinnerSet = true;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void hide() {
        binding.rlMain.setVisibility(View.GONE);

    }

    @Override
    protected void init() {

        binding.gpsSwitch.setChecked(viewModel.getObserver().isGpsChecked());

        binding.gpsSwitch.setOnCheckedChangeListener((view, isChecked) -> {
            viewModel.getObserver().setGpsChecked(isChecked);
        });


        selectStyleAdapter = new SelectStyleAdapter(this);
        binding.rvStyle.setAdapter(selectStyleAdapter);
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

            case R.id.btn_next: {
                if (viewModel.getObserver().isGpsChecked())
                    startActivity(new Intent(getApplicationContext(), screenType.equals("Settings") ? MapActivity.class : FindHairStylist.class));
                    //viewModel.getObserver().setScreenHandler(1);

                break;
            }


            case R.id.iv_gender_drop_down:
            case R.id.iv_gender_drop_up:
            case R.id.tv_style_select:
            case R.id.ll_style_select: {
                viewModel.getObserver().setDropDown(!viewModel.getObserver().isDropDown());
                break;
            }

            case R.id.rl_confirm_payment: {
                viewModel.getObserver().setScreenHandler(2);
                break;
            }

            case R.id.rl_thank_you: {
                viewModel.getObserver().setScreenHandler(3);
                break;
            }


        }
    }

    @Override
    public void onBackPressed() {
        if (viewModel.getObserver().getScreenHandler() == 0) {
            super.onBackPressed();
        }else
            viewModel.getObserver().setScreenHandler(viewModel.getObserver().getScreenHandler() - 1);
    }

}