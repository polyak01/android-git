package com.polyakov.androidgithubclient.presenter;

import android.support.annotation.NonNull;

import com.polyakov.androidgithubclient.R;
import com.polyakov.androidgithubclient.model.Repository;
import com.polyakov.androidgithubclient.view.interfaces.SearchView;

import ru.arturvasilov.rxloader.LifecycleHandler;

/**
 * Created by SnowFlake on 17.10.2016.
 */

public class SearchPresenter {

    private final LifecycleHandler mLifecycleHandler;
    private final SearchView mView;

    public SearchPresenter(@NonNull LifecycleHandler lifecycleHandler,
                                @NonNull SearchView view) {
        mLifecycleHandler = lifecycleHandler;
        mView = view;
    }


    public void onItemClick(@NonNull Repository repository) {
        mView.showCommits(repository);
    }

    public void loadRepositories() {
        SearchRepositoryProvider.searchGithubRepository()
                .repositories("e-contact")
                .doOnSubscribe(mView::showLoading)
                .doOnTerminate(mView::hideLoading)
                .compose(mLifecycleHandler.load(R.id.repositories_request))
                .subscribe(mView::showRepositories, throwable -> mView.showError());
    }

}
