package com.qwiktrips.user.app.ui.signup;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.Task;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.qwiktrips.user.app.R;
import com.qwiktrips.user.app.databinding.ActivitySignupBinding;
import com.qwiktrips.user.app.databinding.LayoutProgressDialogBinding;
import com.qwiktrips.user.app.model.signup.SignupData;
import com.qwiktrips.user.app.model.signup.SignupDatanew;
import com.qwiktrips.user.app.ui.base.BindingActivity;
import com.qwiktrips.user.app.ui.hairstylist.select.SelectHairStylistActivity;
import com.qwiktrips.user.app.ui.login.LoginActivity;
import com.qwiktrips.user.app.ui.map.MapActivity;
import com.qwiktrips.user.app.utils.api.ApiConstant;
import com.qwiktrips.user.app.utils.api.ApiUtils;
import com.qwiktrips.user.app.utils.helper.ViewItemClickHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SignupActivity extends BindingActivity<ActivitySignupBinding> implements ViewItemClickHandler {

    private static final int RC_SIGN_IN = 100;
    private static final String TAG = "Google LogIn";
    private SignupViewModel viewModel;
    private GoogleSignInClient mGoogleSignInClient;
    private GoogleSignInOptions gso;
    private CallbackManager callbackManager;
    private LoginManager loginManager;
    public static final int REQUEST_IMAGE = 100;
    private int requestCode;
    private int resultCode;
    private Uri picUri;

    @Nullable
    private Intent data;

    @Override
    protected int getLayout() {
        return R.layout.activity_signup;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = getViewModel(SignupViewModel.class);
        printHashKey();
        init();
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

    }

    @Override
    protected void init() {
        binding.setViewModel(viewModel);
        binding.setViewHandler(this);
        binding.layoutProfilePhoto.ivProfilePic.setOnClickListener(this::onItemClicked);
        binding.layoutProfilePhoto.ivUpload.setOnClickListener(this::onItemClicked);
        binding.layoutSocial.googleSignin.setOnClickListener(this::onItemClicked);
        binding.layoutSocial.fbSignin.setOnClickListener(this::onItemClicked);
        callbackManager = CallbackManager.Factory.create();
        fbSignIn();
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
            case R.id.iv_gender_drop_down: {
                viewModel.getSignUpObservable().setArrowClicked(!viewModel.getSignUpObservable().isArrowClicked());
                break;
            }

            case R.id.tv_male: {
                viewModel.getSignUpObservable().setGenderSelected(getResources().getString(R.string.male));
                viewModel.getSignUpObservable().setArrowClicked(!viewModel.getSignUpObservable().isArrowClicked());
                break;
            }

            case R.id.tv_female: {
                viewModel.getSignUpObservable().setGenderSelected(getResources().getString(R.string.female));
                viewModel.getSignUpObservable().setArrowClicked(!viewModel.getSignUpObservable().isArrowClicked());
                break;
            }

            case R.id.tv_other: {
                viewModel.getSignUpObservable().setGenderSelected(getResources().getString(R.string.other));
                viewModel.getSignUpObservable().setArrowClicked(!viewModel.getSignUpObservable().isArrowClicked());
                break;
            }

            case R.id.iv_next:
            case R.id.btn_signup:

            case R.id.ll_signup: {
                    viewModel.getSignUpObservable().setScreenObserver(1);

                break;
            }

            case R.id.already_have_account: {
                startActivity(new Intent(this, LoginActivity.class));
                break;
            }

            case R.id.btn_next: {
                showProgressDialog();
                break;
            }

            case R.id.iv_back: {
                onBackPressed();
                break;
            }
            case R.id.google_signin: {
                signIn();
                break;
            }
            case R.id.iv_profile_pic:
            case R.id.iv_upload: {
                selectImage(SignupActivity.this);
                break;
            }
            case R.id.fb_signin: {
                loginManager.logInWithReadPermissions(
                        SignupActivity.this,
                        Arrays.asList(
                                "email"/*,
                                "public_profile",
                                "user_birthday"*/));
                break;
            }
        }
    }

    private void fbSignIn() {
        loginManager
                = LoginManager.getInstance();
        callbackManager
                = CallbackManager.Factory.create();

        loginManager
                .registerCallback(
                        callbackManager,
                        new FacebookCallback<LoginResult>() {

                            @Override
                            public void onSuccess(LoginResult loginResult)
                            {
                                GraphRequest request = GraphRequest.newMeRequest(

                                        loginResult.getAccessToken(),

                                        new GraphRequest.GraphJSONObjectCallback() {

                                            @Override
                                            public void onCompleted(JSONObject object,
                                                                    GraphResponse response)
                                            {

                                                if (object != null) {
                                                    try {
                                                        String name = object.getString("name");
                                                        String email = object.getString("email");
                                                        String fbUserID = object.getString("id");

                                                        disconnectFromFacebook();
                                                        Intent intent=new Intent(SignupActivity.this, MapActivity.class);
                                                        startActivity(intent);

                                                        // do action after Facebook login success
                                                        // or call your API
                                                    }
                                                    catch (JSONException | NullPointerException e) {
                                                        e.printStackTrace();
                                                    }
                                                }
                                            }
                                        });

                                Bundle parameters = new Bundle();
                                parameters.putString(
                                        "fields",
                                        "id, name, email, gender, birthday");
                                request.setParameters(parameters);
                                request.executeAsync();
                            }

                            @Override
                            public void onCancel()
                            {
                                Log.v("LoginScreen", "---onCancel");
                            }

                            @Override
                            public void onError(FacebookException error)
                            {
                                // here write code when get error
                                Log.v("LoginScreen", "----onError: "
                                        + error.getMessage());
                            }
                        });
    }

    private void showProgressDialog() {
        Dialog dialog = new Dialog(this, R.style.AlertStyle);/*android.R.style.Theme_Translucent_NoTitleBar*/
        LayoutProgressDialogBinding progressDialogBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.layout_progress_dialog, null, false);
        progressDialogBinding.progressCircular.setMaxProgress(100);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            int currentProgress;

            @Override
            public void run() {
                currentProgress += 1;
                progressDialogBinding.progressCircular.setProgress(currentProgress, 100);
                if (currentProgress < 100) {
                    handler.postDelayed(this, 10);
                }
                if (currentProgress == 100) {
                    dialog.dismiss();
                    startActivity(new Intent(getApplicationContext(), MapActivity.class));
                    //openNext("Settings");
                }
            }
        }, 10);

        dialog.setCancelable(false);
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.getWindow().setDimAmount(0.8f);
        dialog.setContentView(progressDialogBinding.getRoot());
        dialog.show();
    }

    @Override
    public void onBackPressed() {
        if (viewModel.getSignUpObservable().getScreenObserver() == 1) {
            viewModel.getSignUpObservable().setScreenObserver(0);
        } else {
            super.onBackPressed();
        }
    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(
                requestCode,
                resultCode,
                data);
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 0:
                    if (resultCode == RESULT_OK && data != null) {
                        Bitmap selectedImage = (Bitmap) data.getExtras().get("data");
                        //binding.includeLayoutProfilePhoto.circleImage.setImageBitmap(selectedImage);
                        Glide.with(this).load(selectedImage).apply(RequestOptions.fitCenterTransform()).apply(RequestOptions.circleCropTransform()).into(binding.layoutProfilePhoto.ivProfilePic);

                    }

                    break;
                case 1:
                    if (resultCode == RESULT_OK && data != null) {
                        Uri selectedImage = data.getData();
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        if (selectedImage != null) {
                            Cursor cursor = getContentResolver().query(selectedImage,
                                    filePathColumn, null, null, null);
                            if (cursor != null) {
                                cursor.moveToFirst();

                                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                                String picturePath = cursor.getString(columnIndex);
                                //binding.includeLayoutProfilePhoto.circleImage.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                                Glide.with(this).load(BitmapFactory.decodeFile(picturePath)).apply(RequestOptions.fitCenterTransform()).apply(RequestOptions.circleCropTransform()).into(binding.layoutProfilePhoto.ivProfilePic);
                                cursor.close();
                            }
                        }

                    }
                    break;
            }
        }

    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            Intent intent=new Intent(SignupActivity.this, MapActivity.class);
            startActivity(intent);
            // Signed in successfully, show authenticated UI.
            //updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            //updateUI(null);
        }
    }
    public void
    printHashKey()
    {

        // Add code to print out the key hash
        try {

            PackageInfo info
                    = getPackageManager().getPackageInfo(
                    "com.qwiktrips.user.app",
                    PackageManager.GET_SIGNATURES);

            for (Signature signature : info.signatures) {

                MessageDigest md
                        = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:",
                        Base64.encodeToString(
                                md.digest(),
                                Base64.DEFAULT));
            }
        }

        catch (PackageManager.NameNotFoundException e) {
        }

        catch (NoSuchAlgorithmException e) {
        }
    }
    public void disconnectFromFacebook()
    {
        if (AccessToken.getCurrentAccessToken() == null) {
            return; // already logged out
        }

        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/me/permissions/",
                null,
                HttpMethod.DELETE,
                new GraphRequest
                        .Callback() {
                    @Override
                    public void onCompleted(GraphResponse graphResponse)
                    {
                        LoginManager.getInstance().logOut();
                    }
                })
                .executeAsync();
    }


    private void CropImage() {
        try {

            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            cropIntent.setDataAndType(picUri, "image/*");
            cropIntent.putExtra("crop", "true");
            cropIntent.putExtra("aspectX", 2);
            cropIntent.putExtra("aspectY", 2);
            cropIntent.putExtra("outputX", 256);
            cropIntent.putExtra("outputY", 256);
            cropIntent.putExtra("return-data", true);
            startActivityForResult(cropIntent, 2);
        }
        catch (ActivityNotFoundException e) {

            Toast.makeText(this, "Your device is not supportting the crop action", Toast.LENGTH_SHORT);

        }
    }
    private void selectImage(Context context) {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};

        android.app.AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choose your profile picture");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo")) {
                    if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                    {
                        requestPermissions(new String[]{Manifest.permission.CAMERA}, 0);
                    }
                    else
                    {
                        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent, 0);
                    }

                } else if (options[item].equals("Choose from Gallery")) {
                    if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                    {
                        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                    }
                    else
                    {
                        Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(pickPhoto, 1);
                    }
                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 0)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, 0);
                //CropImage();
            }
            else
            {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
        if (requestCode == 1)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "Storage permission granted", Toast.LENGTH_LONG).show();
                Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto, 1);
                //CropImage();
            }
            else
            {
                Toast.makeText(this, "Storage permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

/*
    private void normalSignUp() {
        if (ApiUtils.checkInternet(context)) {
            getSignUpObservable().setProgressBar(true);
            SignupViewModel.SignupRequest request = new SignupViewModel.SignupRequest();
            request.setFullname(request.getFullname());
            request.setMobile(request.getMobile());
            request.setImage(request.getImage());
            request.setGender(request.getGender());
            getApiRepository()
                    .signup(
                            request,
                            new Observer<SignupData>() {
                                @Override
                                public void onSubscribe(Disposable d) {
                                    getDisposableLiveData().postValue(d);
                                }
                                @Override
                                public void onNext(@NonNull SignupData signupData) {
                                    //  getObserver().setProgressBar(false);
                                    if (signupData.getSuccess().equals(ApiConstant.SUCCESSFUL)) {
                                        Log.e("sussess","signup successfully");
                                        //successfully received data
                                    } else if (signupData.getSuccess().equals(ApiConstant.ERROR)) {
                                        Log.e("error","signup erro");
                                    }
                                }
                                @Override
                                public void onError(Throwable e) {
                                    // Timber.tag("NORMAL SIGNUP").d(e);
                                    getSignUpObservable().setProgressBar(false);
                                    getMsgLiveData().postValue(e.getLocalizedMessage());
                                }
                                @Override
                                public void onComplete() {

                                }
                            }
                    );
        } else {
            getMsgLiveData().setValue(context.getString(R.string.msg_no_internet));
        }
    }
*/




}
