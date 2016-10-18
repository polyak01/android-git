package com.polyakov.androidgithubclient.view.interfaces;

import android.support.annotation.NonNull;

import com.polyakov.androidgithubclient.model.RepositoriesResponse;
import rx.Observable;

/**
 * @author  Yaroslav
 */

public interface ISearchGithubRepository {

    @NonNull
    Observable<RepositoriesResponse> repositories(String repositoryName);
}
