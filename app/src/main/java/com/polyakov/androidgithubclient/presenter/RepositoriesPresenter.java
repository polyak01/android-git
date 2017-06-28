package com.polyakov.androidgithubclient.presenter;

import android.support.annotation.NonNull;

import com.polyakov.androidgithubclient.R;
import com.polyakov.androidgithubclient.models.Repository;
import com.polyakov.androidgithubclient.view.interfaces.RepositoriesView;

import ru.arturvasilov.rxloader.LifecycleHandler;

/**
 * @author Yaroslav
 */

public class RepositoriesPresenter {

    private final LifecycleHandler mLifecycleHandler;
    private final RepositoriesView mView;

    public RepositoriesPresenter(@NonNull LifecycleHandler lifecycleHandler,
                                 @NonNull RepositoriesView view) {
        mLifecycleHandler = lifecycleHandler;
        mView = view;
    }

    public void init() {
        RepositoryProvider.provideGithubRepository()
                .repositories()
                .doOnSubscribe(mView::showLoading)
                .doOnTerminate(mView::hideLoading)
                .compose(mLifecycleHandler.load(R.id.repositories_request))
                .subscribe(mView::showRepositories, throwable -> mView.showError());
    }

    public void onItemClick(@NonNull Repository repository) {
        mView.showCommits(repository);
    }
}
