package com.qwiktrips.user.app.ui.paymentnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.qwiktrips.user.app.R;
import com.qwiktrips.user.app.databinding.LayoutConfirmationPageBinding;
import com.qwiktrips.user.app.databinding.LayoutPaymentOptionBinding;

public class PayActivity extends AppCompatActivity {
    LayoutPaymentOptionBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = LayoutPaymentOptionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.rlConfirmPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity(new Intent(PayActivity.this,PatConfirmActivity.class));
            }
        });
        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}