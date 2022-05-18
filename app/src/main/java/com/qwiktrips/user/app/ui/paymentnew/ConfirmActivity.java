package com.qwiktrips.user.app.ui.paymentnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.qwiktrips.user.app.R;
import com.qwiktrips.user.app.ui.booked.BookedAndFavouritesActivity;
import com.qwiktrips.user.app.ui.home.HomeActivity;
import com.qwiktrips.user.app.ui.map.MapActivity;
import com.qwiktrips.user.app.ui.profile.ProfileActivity;
import com.qwiktrips.user.app.ui.profile.userprofile.UserProfileActivity;

public class ConfirmActivity extends AppCompatActivity {

    RelativeLayout relativeLayout;
    //ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_appointment_confirmed);

        relativeLayout = findViewById(R.id.rl_get_your_receipt);
       // imageView = findViewById(R.id.iv_back);

//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(ConfirmActivity.this, BookedAndFavouritesActivity.class);
//            }
//        });

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConfirmActivity.this, MapActivity.class);
                startActivity(intent);

            }
        });

    }
}