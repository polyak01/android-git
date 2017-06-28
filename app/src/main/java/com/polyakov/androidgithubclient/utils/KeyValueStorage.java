package com.polyakov.androidgithubclient.utils;

import android.support.annotation.NonNull;

import com.orhanobut.hawk.Hawk;

import rx.Observable;

/**
 * @author Yaroslav
 */
public interface KeyValueStorage {

    String TOKEN_KEY = "github_token";
    String USER_NAME_KEY = "user_name";
    String WALKTHROUGH_PASSED_KEY = "walkthrough_passed";

    void saveToken(@NonNull String token);

    @NonNull
    String getToken();

    void saveUserName(@NonNull String userName);

    @NonNull
    Observable<String> getUserName();

    void saveWalkthroughPassed();

    boolean isWalkthroughPassed();
}
