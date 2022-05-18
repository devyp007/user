package com.qwiktrips.user.app.apirepository;

import android.util.Log;
import androidx.annotation.NonNull;
import com.google.gson.GsonBuilder;
import com.qwiktrips.user.app.apiclient.ApiInterface;
import com.qwiktrips.user.app.model.signup.SignupData;
import com.qwiktrips.user.app.session.UserSession;
import com.qwiktrips.user.app.ui.signup.SignupViewModel;
import javax.inject.Inject;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;

public class ApiRepository {
    private final Retrofit.Builder builder;
    private final OkHttpClient.Builder okhttpBuilder;
    private GsonBuilder gsonBuilder;
    private ApiInterface headerLess;
    private ApiInterface withHeader;
    private final UserSession userSession;

    @Inject
    public ApiRepository(Retrofit.Builder builder,
                         OkHttpClient.Builder clientBuilder,
                         GsonBuilder gsonBuilder,
                         UserSession userSession) {
        this.builder = builder;
        this.gsonBuilder = gsonBuilder;
        this.okhttpBuilder = clientBuilder;
        this.userSession = userSession;
    }

    ApiInterface getHeaderLessApi() {
        if (headerLess == null) {
            headerLess = builder.build().create(ApiInterface.class);
        }
        return headerLess;
    }
    ApiInterface getHeaderApi() {
        if (withHeader == null) {
            okhttpBuilder
                    .addInterceptor(chain -> {
                        String token = userSession.getUserToken() == null ? "" : userSession.getUserToken();
                        Log.e("Authorization", token);
                        Request request = chain.request().newBuilder().addHeader("Authorization", token).build();
                        return chain.proceed(request);
                    });
            withHeader = builder.client(okhttpBuilder.build()).build().create(ApiInterface.class);
        }

        return withHeader;
    }

public void signup(@NonNull SignupViewModel.SignupRequest signupRequest,
                   @NonNull Observer<SignupData> observer) {
    getHeaderLessApi()
            .getSignup(signupRequest)
            .subscribeOn(Schedulers.single())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer);
}

}
