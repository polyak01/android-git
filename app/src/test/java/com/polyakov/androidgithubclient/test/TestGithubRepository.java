package com.polyakov.androidgithubclient.test;

import android.support.annotation.NonNull;

import com.polyakov.androidgithubclient.models.Authorization;
import com.polyakov.androidgithubclient.models.Repository;
import com.polyakov.androidgithubclient.view.interfaces.GithubRepository;

import java.util.List;

import rx.Observable;

/**
 * @author Yaroslav Polyakov
 *         © 28.06.17 https://github.com/polyak01
 */

public class TestGithubRepository implements GithubRepository {

    @NonNull
    @Override
    public Observable<List<Repository>> repositories() {
        return Observable.empty();
    }

    @NonNull
    @Override
    public Observable<Authorization> auth(@NonNull String login, @NonNull String password) {
        return Observable.empty();
    }
}


