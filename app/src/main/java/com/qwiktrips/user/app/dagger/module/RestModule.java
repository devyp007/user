package com.qwiktrips.user.app.dagger.module;

import android.util.Log;
import com.google.gson.GsonBuilder;
import com.qwiktrips.user.app.apirepository.ApiRepository;
import com.qwiktrips.user.app.application.QuickTripApp;
import com.qwiktrips.user.app.session.UserSession;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RestModule {
    @Provides
    @Singleton
    HttpLoggingInterceptor provideLoggingInterceptor() {
        //logging
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(@NotNull String s) {
                Log.e("OkHttp", s);
            }
        });

//        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

    @Provides
    @Singleton
    OkHttpClient.Builder provideOkHttpClientBuilder(HttpLoggingInterceptor httpLoggingInterceptor) {
        //client
        return new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor);
    }

    @Provides
    @Singleton
    GsonBuilder provideGsonBuilder() {
        //gson
        return new GsonBuilder()
                .setDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz");
    }

    @Provides
    @Singleton
    Retrofit.Builder provideRetrofitBuilder(OkHttpClient.Builder clientBuilder,
                                            GsonBuilder gsonBuilder,
                                            QuickTripApp quickTripApp) {
        return new Retrofit.Builder()
                .baseUrl("https://3gllf96ewi.execute-api.us-east-1.amazonaws.com/api/v1/")
                .client(clientBuilder.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()));
    }

    @Provides
    @Singleton
    ApiRepository providerApiResponse(Retrofit.Builder builder,
                                      OkHttpClient.Builder clientBuilder,
                                      GsonBuilder gsonBuilder,
                                      UserSession userSession) {
        return new ApiRepository(builder, clientBuilder, gsonBuilder, userSession);
    }

}
