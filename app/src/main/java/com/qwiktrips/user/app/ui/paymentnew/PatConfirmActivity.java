package com.qwiktrips.user.app.ui.paymentnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.qwiktrips.user.app.R;
import com.qwiktrips.user.app.databinding.LayoutConfirmationPageBinding;
import com.qwiktrips.user.app.ui.booked.BookedAndFavouritesActivity;

public class PatConfirmActivity extends AppCompatActivity {
    LayoutConfirmationPageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=LayoutConfirmationPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.rlThankYou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PatConfirmActivity.this,ConfirmActivity.class));
            }
        });
//        binding.ivBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                BookedAndFavouritesActivity.super.onBackPressed();
//            }
//        });

    }
}