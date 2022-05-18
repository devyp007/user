package com.qwiktrips.user.app.ui.map;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.qwiktrips.user.app.R;
import com.qwiktrips.user.app.databinding.ActivityMapBinding;
import com.qwiktrips.user.app.databinding.LayoutLogoutBinding;
import com.qwiktrips.user.app.ui.appointment.MyAppointmentActivity;
import com.qwiktrips.user.app.ui.base.BindingActivity;
import com.qwiktrips.user.app.ui.cart.CartActivity;
import com.qwiktrips.user.app.ui.notification.NotificationActivity;
import com.qwiktrips.user.app.ui.payment.PaymentMethodActivity;
import com.qwiktrips.user.app.ui.providers.PreferredProvidersActivity;
import com.qwiktrips.user.app.utils.AppConstants;
import com.qwiktrips.user.app.utils.helper.DataItemCallback;
import com.qwiktrips.user.app.utils.helper.ViewItemClickHandler;

import java.util.Locale;

public class MapActivity extends BindingActivity<ActivityMapBinding> implements ViewItemClickHandler, OnMapReadyCallback,
        GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener, GoogleMap.OnMapClickListener {

    private MapViewModel viewModel;
    private GoogleMap googleMap;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationCallback locationCallback;
    private LocationRequest locationRequest;
    private Geocoder geocoder;
    private int currentlyChanged = 1;
    private boolean istoggleOn;
    Boolean isAlreadyOpened = false;

    @Override
    protected int getLayout() {
        return R.layout.activity_map;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = getViewModel(MapViewModel.class);
        binding.setViewHandler(this);
        binding.setViewModel(viewModel);
        binding.setType(0);
        init();
    }

    @Override
    protected void init() {
        binding.includeDrawer.setType(1);

        Glide.with(this).load(R.drawable.iimg).apply(RequestOptions.circleCropTransform()).into(binding.includeDrawer.includeLongDrawer.ivUserPic);
        Glide.with(this).load(R.drawable.user).into(binding.includeLayoutMap.includeUserDetail.ivHairstylist);

        MapServiceAdapter adapter = new MapServiceAdapter((o, position, value) -> {
            if (value == 1) {
                openNext("Location");
//                startActivity(new Intent(this, SelectHairStylistActivity.class));
            }

        });
        binding.includeLayoutMap.rvMap.setAdapter(adapter);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_location);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        geocoder = new Geocoder(this, Locale.getDefault());

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                if (googleMap != null) {
                    LatLng latLng = new LatLng(locationResult.getLastLocation().getLatitude(), locationResult.getLastLocation().getLongitude());
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 11));
                }
            }
        };

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
            case R.id.iv_menu:
            case R.id.ll_help:

            case R.id.iv_close: {
                openCloseDrawer(binding.drawerLayout);
                break;
            }
            case R.id.ll_my_qwiktrips:{
                startActivity(new Intent(getApplicationContext(), MyAppointmentActivity.class));
                openCloseDrawer(binding.drawerLayout);
                break;
            }
            case R.id.ll_settings: {
             openNext("Settings");
//                startActivity(new Intent(getApplicationContext(), SelectHairStylistActivity.class).putExt);
                openCloseDrawer(binding.drawerLayout);

                break;
            }
            case R.id.ll_invite_friends: {
                startActivity(new Intent(getApplicationContext(), PreferredProvidersActivity.class));
                openCloseDrawer(binding.drawerLayout);
                break;
            }
            case R.id.ll_profile: {
                openProfile();
                openCloseDrawer(binding.drawerLayout);
                break;
            }
            case R.id.ll_wallet: {
                startActivity(new Intent(getApplicationContext(), PaymentMethodActivity.class));
                openCloseDrawer(binding.drawerLayout);
                break;
            }
            case R.id.btn_learn_more: {
                //binding.setType(1);
                break;
            }
            case R.id.iv_home: {
                callHome();
                break;
            }

            case R.id.ll_notifications: {
                openCloseDrawer(binding.drawerLayout);
                startActivity(new Intent(getApplicationContext(), NotificationActivity.class));
                break;
            }

            case R.id.ll_cart: {
                openCloseDrawer(binding.drawerLayout);
                startActivity(new Intent(getApplicationContext(), CartActivity.class));
                break;
            }
            case R.id.ll_logout:{
              /*  openCloseDrawer(binding.drawerLayout);
                LoginManager.getInstance().logOut();
                startActivity(new Intent(this, LoginActivity.class));*/
                showLogoutDialog(1);

            }


            case R.id.iv_slider: {
                if (viewModel.getDrawerObserver().getType() == 1) {
                    viewModel.getDrawerObserver().setType(2);
                    binding.includeDrawer.setType(2);
                    // binding.navViewMain.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
                } else {
                    // binding.navViewMain.getLayoutParams().width = ViewGroup.LayoutParams.WRAP_CONTENT;
                    viewModel.getDrawerObserver().setType(1);
                    binding.includeDrawer.setType(1);
                }
                break;
            }

            case R.id.iv_slider_long: {
                // binding.navViewMain.getLayoutParams().width = ViewGroup.LayoutParams.WRAP_CONTENT;
                viewModel.getDrawerObserver().setType(1);
                binding.includeDrawer.setType(1);
                //openCloseDrawer(binding.drawerLayout);
            }
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
       /* this.googleMap.setOnMyLocationButtonClickListener(this);
        this.googleMap.setOnMyLocationClickListener(this);
        this.googleMap.setOnMapClickListener(this);

        enableCurrentLocation();*/

        LatLng sydney = new LatLng(30.73, 76.78);
        this.googleMap.addMarker(new MarkerOptions()
                .position(sydney)
                .title("Barber")
                .icon(BitmapFromVector(getApplicationContext(), R.drawable.mapicon1)));

        LatLng sydney1 = new LatLng(40.73, 86.78);
        this.googleMap.addMarker(new MarkerOptions()
                .position(sydney1)
                .title("Barber")
                .icon(BitmapFromVector(getApplicationContext(), R.drawable.mapicon2)));

        LatLng sydney2 = new LatLng(50.73, 96.78);
        this.googleMap.addMarker(new MarkerOptions()
                .position(sydney2)
                .title("Barber")
                .icon(BitmapFromVector(getApplicationContext(), R.drawable.mapicon3)));

        LatLng sydney3 = new LatLng(60.73, 76.78);
        this.googleMap.addMarker(new MarkerOptions()
                .position(sydney3)
                .title("Barber")
                .icon(BitmapFromVector(getApplicationContext(), R.drawable.mapicon1)));
    }
    private BitmapDescriptor BitmapFromVector(Context context, int vectorResId) {
        // below line is use to generate a drawable.
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);

        // below line is use to set bounds to our vector drawable.
        vectorDrawable.setBounds(0, 0,100, 100);

        // below line is use to create a bitmap for our
        // drawable which we have added.
        Bitmap bitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);

        // below line is use to add bitmap in our canvas.
        Canvas canvas = new Canvas(bitmap);

        // below line is use to draw our
        // vector drawable in canvas.
        vectorDrawable.draw(canvas);

        // after generating our bitmap we are returning our bitmap.
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    private void enableCurrentLocation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkThePermission(getResources().getString(R.string.msg_allow_us_location_permission),
                    getResources().getString(R.string.msg_permission_denied_not_able_detect_location),
                    new DataItemCallback<Integer, Integer>() {
                        @SuppressLint("MissingPermission")
                        @Override
                        public void onItemData(Integer result, Integer integer2) {
                            if (result == AppConstants.PERMISSION_ACCEPTED) {
                                if (googleMap != null) {
                                    googleMap.setMyLocationEnabled(true);
                                    getLastLocation();
                                }
                            }
                        }
                    }, Manifest.permission.ACCESS_FINE_LOCATION);
        } else {
            if (googleMap != null) {
                googleMap.setMyLocationEnabled(true);
                getLastLocation();
            }
        }


    }

    @SuppressLint("MissingPermission")
    private void getLastLocation() {
        if (locationRequest == null) {
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
                        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.builder().target(latLng).zoom(15).build()));

                    }
                }
            });
        }
    }

    @Override
    public void onMapClick(LatLng latLng) {

    }

    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {

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
        if (binding.getType() != 0) {
            binding.setType(0);
        } else {
            super.onBackPressed();
        }
    }
}