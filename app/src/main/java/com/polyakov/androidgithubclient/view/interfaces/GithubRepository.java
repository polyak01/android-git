package com.polyakov.androidgithubclient.view.interfaces;

import android.support.annotation.NonNull;

import com.polyakov.androidgithubclient.model.Authorization;
import com.polyakov.androidgithubclient.model.Repository;

import java.util.List;

import rx.Observable;

/**
 * @author Yaroslav
 */

public interface GithubRepository {

    @NonNull
    Observable<List<Repository>> repositories();

    @NonNull
    Observable<Authorization> auth(@NonNull String login, @NonNull String password);
}
