package com.qwiktrips.user.app.apiclient;

import androidx.annotation.NonNull;

import com.qwiktrips.user.app.model.signup.SignupData;
import com.qwiktrips.user.app.ui.login.LoginViewModel;
import com.qwiktrips.user.app.ui.signup.SignupViewModel;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {


    @POST("signup")
    Observable<SignupData> getSignup(@Body SignupViewModel.SignupRequest signupRequest);

//    @Multipart
//    @POST("upload")
//    Observable<ApiResponse<UploadResponse>> upload(@Part MultipartBody.Part imageFileBody);
//
//
//    @POST("api/v1/login")
//    Observable<ApiResponse<TokenResponse>> login(@NonNull @Body LoginViewModel.UserLoginRequest request);
//
//    @POST("api/v1/verify-otp")
//    Observable<ApiResponse<TokenResponse>> verifyOTP(@NonNull @Body HandlerViewModel.OTPRequest request);


}
