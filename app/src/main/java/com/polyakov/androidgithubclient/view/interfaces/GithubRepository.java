package com.polyakov.androidgithubclient.view.interfaces;

import android.support.annotation.NonNull;

import com.polyakov.androidgithubclient.models.Authorization;
import com.polyakov.androidgithubclient.models.Repository;

import java.util.List;

import rx.Observable;

/**
 * @author Yaroslav Polyakov
 *         © 2016 https://github.com/polyak01
 */

public interface GithubRepository {

    @NonNull
    Observable<List<Repository>> repositories();

    @NonNull
    Observable<Authorization> auth(@NonNull String login, @NonNull String password);
}
