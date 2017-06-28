package com.polyakov.androidgithubclient.presenter.api;

import android.support.annotation.NonNull;

import com.polyakov.androidgithubclient.presenter.RepositoryProvider;
import com.polyakov.androidgithubclient.utils.KeyValueStorage;
import com.polyakov.androidgithubclient.utils.TextUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author Yaroslav
 */

class ApiKeyInterceptor implements Interceptor {

    private final String mToken;

    private ApiKeyInterceptor() {
        mToken = RepositoryProvider.provideKeyValueStorage().getToken();
    }

    @NonNull
    public static Interceptor create() {
        return new ApiKeyInterceptor();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        if (TextUtils.isEmpty(mToken)) {
            return chain.proceed(chain.request());
        }
        Request request = chain.request().newBuilder()
                .addHeader("Authorization", String.format("%s %s", "token", mToken))
                .build();
        return chain.proceed(request);
    }
}
