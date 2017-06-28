package com.polyakov.androidgithubclient.test;

import android.support.annotation.NonNull;

import com.polyakov.androidgithubclient.utils.KeyValueStorage;

import rx.Observable;

/**
 * @author Yaroslav Polyakov
 *         © 28.06.17 https://github.com/polyak01
 */

public class TestKeyValueStorage implements KeyValueStorage {

    @Override
    public void saveToken(@NonNull String token) {
        // Do nothing
    }

    @NonNull
    @Override
    public String getToken() {
        return "";
    }

    @Override
    public void saveUserName(@NonNull String userName) {
        // Do nothing
    }

    @NonNull
    @Override
    public Observable<String> getUserName() {
        return Observable.empty();
    }

    @Override
    public void saveWalkthroughPassed() {
        // Do nothing
    }

    @Override
    public boolean isWalkthroughPassed() {
        return false;
    }
}
