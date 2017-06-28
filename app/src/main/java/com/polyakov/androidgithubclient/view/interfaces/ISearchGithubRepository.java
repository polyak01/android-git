package com.polyakov.androidgithubclient.view.interfaces;

import android.support.annotation.NonNull;

import com.polyakov.androidgithubclient.models.RepositoriesResponse;
import rx.Observable;

/**
 * @author Yaroslav Polyakov
 *         © 2016 https://github.com/polyak01
 */

public interface ISearchGithubRepository {

    @NonNull
    Observable<RepositoriesResponse> repositories(String repositoryName);
}
