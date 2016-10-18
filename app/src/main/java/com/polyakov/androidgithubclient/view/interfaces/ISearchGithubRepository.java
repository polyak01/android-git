package com.polyakov.androidgithubclient.view.interfaces;

import android.support.annotation.NonNull;

import com.polyakov.androidgithubclient.model.RepositoriesResponse;
import com.polyakov.androidgithubclient.model.Repository;

import java.util.List;

import rx.Observable;

/**
 * Created by SnowFlake on 17.10.2016.
 */

public interface ISearchGithubRepository {

    @NonNull
    Observable<RepositoriesResponse> repositories(String repositoryName);
}
