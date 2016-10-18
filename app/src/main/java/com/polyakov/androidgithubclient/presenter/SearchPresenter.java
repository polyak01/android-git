package com.polyakov.androidgithubclient.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.polyakov.androidgithubclient.R;
import com.polyakov.androidgithubclient.model.Repository;
import com.polyakov.androidgithubclient.view.interfaces.SearchView;

import ru.arturvasilov.rxloader.LifecycleHandler;

/**
 * @author Yaroslav
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

    public void loadRepositories(String nameOfRepo) {
        Log.i("SerchPresnter", "Start Load Repo");
        SearchRepositoryProvider.searchGithubRepository()
                .repositories(nameOfRepo)
                .doOnSubscribe(mView::showLoading)
                .doOnTerminate(mView::hideLoading)
              //  .compose(mLifecycleHandler.load(R.id.search_request))
                .subscribe(it -> { mView.showRepositories(it.getList()); }, throwable -> mView.showError());

        Log.i("SerchPresnter", "has Finished Load Repo");
    }

}
