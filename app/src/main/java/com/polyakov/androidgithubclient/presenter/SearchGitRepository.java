package com.polyakov.androidgithubclient.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.polyakov.androidgithubclient.model.RepositoriesResponse;
import com.polyakov.androidgithubclient.presenter.api.ApiFactory;
import com.polyakov.androidgithubclient.view.interfaces.ISearchGithubRepository;

import ru.arturvasilov.rxloader.RxUtils;
import rx.Observable;

/**
 * @author Yaroslav
 */

public class SearchGitRepository implements ISearchGithubRepository {
    // TODO: 18.10.2016
    @NonNull
    @Override
    public Observable<RepositoriesResponse> repositories(String repositoryName) {
        return ApiFactory.getGithubService()
                .repositorySearching(repositoryName)
                .compose(RxUtils.async())
                .doOnError(Throwable::printStackTrace)
                .doOnNext( rep ->{ Log.i("TAGGG", "" + rep.getList().size());});
    }
}
