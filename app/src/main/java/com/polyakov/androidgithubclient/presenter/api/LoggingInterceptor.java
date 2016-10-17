package com.polyakov.androidgithubclient.presenter.api;

import android.support.annotation.NonNull;

import com.polyakov.androidgithubclient.BuildConfig;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

import static okhttp3.logging.HttpLoggingInterceptor.Level;
/**
 * @author Yaroslav
 */

public final class LoggingInterceptor implements Interceptor {

    private final Interceptor mLoggingInterceptor;

    private LoggingInterceptor() {
        mLoggingInterceptor = new HttpLoggingInterceptor()
                .setLevel(BuildConfig.DEBUG ? Level.BODY : Level.NONE);
    }

    @NonNull
    public static Interceptor create() {
        return new LoggingInterceptor();
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        return mLoggingInterceptor.intercept(chain);
    }

}

